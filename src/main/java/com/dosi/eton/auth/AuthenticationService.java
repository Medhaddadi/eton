package com.dosi.eton.auth;

import com.dosi.eton.Dto.ClientRegistrationRequest;
import com.dosi.eton.config.JwtService;
import com.dosi.eton.models.Client;
import com.dosi.eton.models.STATUS;
import com.dosi.eton.models.Voiture;
import com.dosi.eton.repository.ClientRepository;
import com.dosi.eton.repository.VoitureRepository;
import com.dosi.eton.services.VoitureService;
import com.dosi.eton.token.Token;
import com.dosi.eton.token.TokenRepository;
import com.dosi.eton.token.TokenType;
import com.dosi.eton.user.Role;
import com.dosi.eton.user.User;
import com.dosi.eton.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final ClientRepository clientRepository;
  private final VoitureService voitureService;
 Logger logger = Logger.getLogger(AuthenticationService.class.getName());
  public AuthenticationResponse register(RegisterRequest request) throws Exception {

    Optional<User> existingUser = repository.findByEmail(request.getEmail());
    if (existingUser.isPresent()) {
      throw new Exception("L'utilisateur avec l'email " + request.getEmail() + " existe déjà");
    }
    var user = User.builder()
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .email(request.getEmail())
            .adresse(request.getAdresse())
            .telephone(request.getTelephone())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .build();
    User savedUser;
    try {
      savedUser = repository.save(user);
    } catch (Exception e) {
      throw new Exception("Erreur lors de l'enregistrement de l'utilisateur", e);
    }
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .message("User registered successfully")
            .refreshToken(refreshToken)
            .build();
  }


  public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
    try {
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      request.getEmail(),
                      request.getPassword()
              )
      );
    } catch (BadCredentialsException e) {
      throw new Exception("Incorrect username or password", e);
    } catch (DisabledException e) {
      throw new Exception("User is disabled", e);
    } catch (LockedException e) {
      throw new Exception("User account is locked", e);
    }

    var userOptional = repository.findByEmail(request.getEmail());
    if (!userOptional.isPresent()) {
      throw new Exception("User not found with email: " + request.getEmail());
    }

    var user = userOptional.get();
    String jwtToken;
    String refreshToken;
    try {
      jwtToken = jwtService.generateToken(user);
      refreshToken = jwtService.generateRefreshToken(user);
      revokeAllUserTokens(user);
      saveUserToken(user, jwtToken);
    } catch (Exception e) {
      throw new Exception("Error generating tokens", e);
    }

    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .message("User authenticated successfully")
            .build();
  }


  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.repository.findByEmail(userEmail)
              .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }

  @Transactional
  public AuthenticationResponse registerClientWithCar(ClientRegistrationRequest request) throws Exception {
    Optional<User> existingUser = repository.findByEmail(request.getEmail());
    if (existingUser.isPresent()) {
      throw new Exception("L'utilisateur avec l'email " + request.getEmail() + " existe déjà");
    }
    logger.info("Enregistrement de la voiture");
    var car = voitureService.save(new Voiture(request.getModele(), request.getAnnee(), request.getKilometrage(), request.getVin()));

    logger.info("Enregistrement du client");
    var user = new Client(request.getFirstname(), request.getLastname(), request.getEmail(), request.getAdresse(), request.getTelephone(), passwordEncoder.encode(request.getPassword()), Role.USER);
    user.setVoiture(car);
    Client savedUser;
    try {
      savedUser = clientRepository.save(user);
    } catch (Exception e) {
      throw new RuntimeException("Erreur lors de l'enregistrement de l'utilisateur", e);
    }
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .message("Client enregistré avec succès")
            .build();
  }

  public AuthenticationResponse isAuthenticated(HttpServletRequest request, HttpServletResponse response) {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String accessToken;
    final String userEmail;
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return null;
    }
    accessToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(accessToken);
    if (userEmail != null) {
      var user = this.repository.findByEmail(userEmail)
              .orElseThrow();
      if (jwtService.isTokenValid(accessToken, user)) {
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(accessToken)
                .build();
        return authResponse;
      }
    }
    return null;
  }
}
