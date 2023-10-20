package com.dosi.eton.controllers;

import com.dosi.eton.models.OffreTarifaire;

import com.dosi.eton.services.OffreTarifaireService;
import com.dosi.eton.services.ValidationErrorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import com.dosi.eton.ExeceptionHandler.MessageResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/offres")
@RequiredArgsConstructor
public class OffreController {

    private final OffreTarifaireService offreTarifaireService;
    private final ValidationErrorService validationErrorService;
    Logger logger = Logger.getLogger(OffreController.class.getName());

    @RequestMapping
    public ResponseEntity<?> getAllOffres() {
        try {
            logger.info("calling getAllOffres ...");
            List<OffreTarifaire> offres = offreTarifaireService.getAllOffresTarifaires();
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(new MessageResponse(200, "Liste des offres contient " + offres.size() + " offres", offres));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageResponse(500, e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createOffre(@Valid @RequestBody OffreTarifaire offre, BindingResult result) {
        logger.info("createOffre");

        ResponseEntity<?> errorMap = validationErrorService.validate(result);
        if (errorMap != null) {
            return errorMap;
        }

        try {
            OffreTarifaire savedOffre = offreTarifaireService.save(offre);
            return ResponseEntity.ok().body(new MessageResponse(200, "Offre créée avec succès ", savedOffre));
        } catch (Exception e) {
            logger.warning("Erreur lors de la création de l'offre" + e.getMessage());
            return new ResponseEntity<>(new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchOffre(@NonNull @RequestParam("queryNom") String query) {
        logger.info("searchOffre with query = " + query);
        try {
            List<OffreTarifaire> offres = offreTarifaireService.searchOffre(query);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(new MessageResponse(200, "Liste des offres contient " + offres.size() + " offres", offres));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageResponse(500, e.getMessage()));
        }
    }
}
