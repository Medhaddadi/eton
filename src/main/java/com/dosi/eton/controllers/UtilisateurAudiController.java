package com.dosi.eton.controllers;



import com.dosi.eton.models.UtilisateurAudi;
import com.dosi.eton.services.UtilisateurAudiService;
import com.dosi.eton.services.VoitureService;
import com.dosi.eton.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api/utilisateur-audi")
public class UtilisateurAudiController {
    Logger logger = Logger.getLogger(UtilisateurAudiController.class.getName());
    private UserRepository userRepository;



    private UtilisateurAudiService utilisateurAudiService;

    private VoitureService voitureService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public UtilisateurAudiController(UserRepository userRepository, UtilisateurAudiService utilisateurAudiService, VoitureService voitureService) {
        this.userRepository = userRepository;
        this.utilisateurAudiService = utilisateurAudiService;
        this.voitureService = voitureService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<UtilisateurAudi>> getAllUtilisateurAudi() {
        Set<UtilisateurAudi> utilisateurAudis = utilisateurAudiService.getAllUtilisateurAudi();
        return ResponseEntity.ok(utilisateurAudis);
    }

    @GetMapping("/filterByStatus")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UtilisateurAudi>> filterByStatus(@RequestParam Boolean statut) {
        List<UtilisateurAudi> users = utilisateurAudiService.findByStatut(statut);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/updateStatus/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UtilisateurAudi> updateStatus(@PathVariable Long id, @RequestParam Boolean newStatut) {
        UtilisateurAudi updatedUser = utilisateurAudiService.updateStatut(id, newStatut);
        return ResponseEntity.ok(updatedUser);
    }

}
