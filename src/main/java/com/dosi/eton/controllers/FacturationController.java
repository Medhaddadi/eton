package com.dosi.eton.controllers;

import com.dosi.eton.ExeceptionHandler.MessageResponse;
import com.dosi.eton.models.Client;
import com.dosi.eton.models.Facturation;
import com.dosi.eton.services.CarteService;
import com.dosi.eton.services.ClientService;
import com.dosi.eton.services.FacturationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/facturation")
public class FacturationController {
    private final FacturationService facturationService;
    private final ClientService clientService;
    private final CarteService carteService;

    Logger logger = Logger.getLogger(FacturationController.class.getName());
    @GetMapping("/payer/{id_facture}/facture")
    public ResponseEntity<?> payerFacture(@NonNull @PathVariable("id_facture") Integer idFacture, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        logger.info(" souscrireAbonnement with username = " + username);
        Integer idClient = clientService.getByEmail(username).getId();
        Client client=null;
        try {
            client = clientService.getClientById(idClient);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageResponse(500, "Erreur lors de la souscription à l'abonnement :" + e.getMessage()));
        }
        try {
            logger.info(" payerFacture with idFacture = " + idFacture);
            facturationService.payerFacture(idFacture, client);
            logger.info(" affecterCarte with client = " + client);
            carteService.affecterCarte(client);
            return ResponseEntity.ok().body(new MessageResponse(200, "Facture payée avec succès"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageResponse(500, "Erreur lors de la souscription à l'abonnement :" + e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/factures")
    public ResponseEntity<?> getFactures(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        logger.info(" souscrireAbonnement with username = " + username);
        Integer idClient = clientService.getByEmail(username).getId();

        try {
            Client client = clientService.getClientById(idClient);
            logger.info("return de function getClientById + " + client);
            List<Facturation> facturations = client.getAbonnement().getFacturations();
            return ResponseEntity.ok().body(new MessageResponse(200,"Bonjour "+client.getLastname()+" "+client.getFirstname()+" \n voici vos factures : \n"+
                    "facturations : ",facturations));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageResponse(500, "Erreur lors de la souscription à l'abonnement :" + e.getMessage()));
        }
    }
}
