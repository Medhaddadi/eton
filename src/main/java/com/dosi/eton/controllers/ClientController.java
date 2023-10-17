package com.dosi.eton.controllers;



import com.dosi.eton.Dto.ClientRegistrationRequest;
import com.dosi.eton.auth.AuthenticationResponse;
import com.dosi.eton.auth.AuthenticationService;
import com.dosi.eton.models.Client;
import com.dosi.eton.services.ClientService;
import com.dosi.eton.services.VoitureService;
import com.dosi.eton.user.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            AuthenticationResponse savedClient = service.registerClientWithCar(request);
            return ResponseEntity.ok(savedClient);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error during registration: " + e.getMessage());
        }
    }

}
