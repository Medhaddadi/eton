package com.dosi.eton.controllers;


import com.dosi.eton.ExeceptionHandler.MessageResponse;
import com.dosi.eton.models.Abonnement;
import com.dosi.eton.models.Client;
import com.dosi.eton.services.AbonnementService;
import com.dosi.eton.services.ClientService;
import com.dosi.eton.user.UserController;
import com.dosi.eton.user.UserRepository;
import com.dosi.eton.user.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/abonnements")
@RequiredArgsConstructor
public class AbonnementController {
    private final AbonnementService abonnementService;
    private final ClientService clientService;

    Logger logger = Logger.getLogger(AbonnementController.class.getName());

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/souscrire/{id_offre}/offre")
    public ResponseEntity<?> souscrireAbonnement(
            @NotNull @PathVariable("id_offre") Integer idOffre, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Integer idClient = clientService.getByEmail(userDetails.getUsername()).getId();
        logger.info("souscrireAbonnement with idOffre = " + idOffre + " and idClient = " + idClient);
        Client client = clientService.getClientById(idClient);
        try {
            abonnementService.souscrire(idOffre, idClient);
            String contract = genererContract(client);
            return ResponseEntity.ok().body(new MessageResponse(200, "Abonnement souscrit avec succès" , contract));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageResponse(500, "Erreur lors de la souscription à l'abonnement :" + e.getMessage()));
        }
    }

    public String genererContract(Client client){
        return  "Bonjour " + client.getLastname() + " " + client.getFirstname() + " \n" +
                "Vous avez souscrit à l'abonnement " + client.getAbonnement().getOffre().getNom() + " le " + client.getAbonnement().getDateDebut() + " pour une durée de " + client.getAbonnement().getOffre().getDureeContrat() + " mois. \n" +
                "dans l'offre " + client.getAbonnement().getOffre().getNom() + " vous avez " + client.getAbonnement().getOffre().getPartenaireChargeurHautePuissance() + "heures de chargeur haute puissance . \n Le montant de l'abonnement est de " + client.getAbonnement().getOffre().getFraisMensuels() + "€. pour chaque mois. \n" +
                "Vous pouvez consulter votre facture sur votre espace client. \n" +
                "Cordialement, \n" +
                "L'équipe Eton";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id_abonnement}/abonnement")
    public ResponseEntity<?> deleteAbonnement(@NotNull @PathVariable("id_abonnement") Integer idAbonnement) {
        logger.info(" deleteAbonnement with idAbonnement = " + idAbonnement);
        try {
            abonnementService.deleteAbonnement(idAbonnement);
            logger.info("return de function deleteAbonnement  " + idAbonnement);
            return ResponseEntity.ok().body(new MessageResponse(200, "Abonnement supprimé avec succès"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageResponse(500, "Erreur lors de la suppression de l'abonnement :" + e.getMessage()));
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllAbonnements")
    public ResponseEntity<?> getAllAbonnements() {
        logger.info("getAllAbonnements");
        try {
            List<Abonnement> abonnements = abonnementService.getAllAbonnements();
            return ResponseEntity.ok().body(new MessageResponse(200, "Liste des abonnements contient " + abonnements.size() + " abonnements", abonnements));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageResponse(500, "Erreur lors de la récupération des abonnements :" + e.getMessage()));
        }
    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping("/monAbonnement")
    public ResponseEntity<?> getAbonnementByClient(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Integer idClient = clientService.getByEmail(userDetails.getUsername()).getId();

        logger.info("getAbonnementByClient with idClient = " + idClient);
        try {
            Abonnement abonnement = clientService.getClientById(idClient).getAbonnement();
            return ResponseEntity.ok().body(new MessageResponse(200, "Bonjour " + userDetails.getUsername() + " \n voici votre abonnement : \n" +
                    "abonnement : " , abonnement));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageResponse(500, "Erreur lors de la récup de l'abonnement :" + e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/getContract")
    public ResponseEntity<?> getContract(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Integer idClient = clientService.getByEmail(userDetails.getUsername()).getId();

        logger.info("getAbonnementByClient with idClient = " + idClient);
        try {
            Client client = clientService.getClientById(idClient);
            Abonnement abonnement = client.getAbonnement();
            String contract = "Bonjour Monsieur/Madame " + client.getLastname() + " " + client.getFirstname() + " \n" +
                    "Vous avez souscrit à l'abonnement " + abonnement.getOffre().getNom() + " le " + abonnement.getDateDebut() + " pour une durée de " + abonnement.getOffre().getDureeContrat() + " mois. \n" +
                    "dans l'offre " + abonnement.getOffre().getNom() + " vous avez " + abonnement.getOffre().getPartenaireChargeurHautePuissance() + "heures de chargeur haute puissance . \n Le montant de l'abonnement est de " + abonnement.getOffre().getFraisMensuels() + "€. pour chaque mois. \n" +
                    "Vous pouvez consulter votre facture sur votre espace client. \n" +
                    "Cordialement, \n" +
                    "L'équipe Eton";
            return ResponseEntity.ok(contract);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageResponse(500, "Erreur lors de la récup de l'abonnement :" + e.getMessage()));
        }
    }
}


