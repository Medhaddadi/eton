package com.dosi.eton.controllers;



import com.dosi.eton.Dto.ClientRegistrationRequest;
import com.dosi.eton.ExeceptionHandler.MessageResponse;
import com.dosi.eton.auth.AuthenticationResponse;
import com.dosi.eton.auth.AuthenticationService;
import com.dosi.eton.models.Client;
import com.dosi.eton.models.Facturation;
import com.dosi.eton.services.ClientService;
import com.dosi.eton.services.VoitureService;
import com.dosi.eton.user.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientController {
    Logger logger = Logger.getLogger(ClientController.class.getName());
    private UserRepository userRepository;
    private final AuthenticationService service;
    private ClientService clientService;
    private VoitureService voitureService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public ClientController(UserRepository userRepository, AuthenticationService service, ClientService ClientService, VoitureService voitureService) {
        this.userRepository = userRepository;
        this.service = service;
        this.clientService = ClientService;
        this.voitureService = voitureService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<Client>> getAllUtilisateurAudi() {
        Set<Client> clients = clientService.getAllUtilisateurAudi();
        return ResponseEntity.ok(clients);
    }

    @PostMapping("/registerWithCar")
    public ResponseEntity<?> registerClientWithCar(
            @Valid @RequestBody ClientRegistrationRequest request,
            BindingResult result
    ) {
        logger.info("registerClientWithCar with request = " + request);
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(new MessageResponse(400, errors.toString()));
        }
        try {
            AuthenticationResponse savedClient = service.registerClientWithCar(request);
            return ResponseEntity.ok(savedClient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse(500, "Erreur lors de l'enregistrement du client :" + e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/auth/client")
    public ResponseEntity<?> getClientById(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        logger.info(" souscrireAbonnement with username = " + username);
        Integer idClient = clientService.getByEmail(username).getId();

        try {
            Client client = clientService.getClientById(idClient);
            logger.info("return de function getClientById + " + client);

            return ResponseEntity.ok(client);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageResponse(500, "Erreur lors de la souscription à l'abonnement :" + e.getMessage()));
        }
    }

    // voir son profil
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        logger.info(" souscrireAbonnement with username = " + username);
        Integer idClient = clientService.getByEmail(username).getId();

        try {
            Client client = clientService.getClientById(idClient);
            logger.info("return de function getClientById + " + client);

            return ResponseEntity.ok().body(new MessageResponse(200, "Bonjour " + userDetails.getUsername() + " \n voici votre profil : \n" +
                    "profil : " + client));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageResponse(500, "Erreur lors de la souscription à l'abonnement :" + e.getMessage()));
        }
    }


}
