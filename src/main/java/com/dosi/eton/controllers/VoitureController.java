package com.dosi.eton.controllers;

import com.dosi.eton.ExeceptionHandler.MessageResponse;
import com.dosi.eton.services.VoitureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/voitures")
@PreAuthorize("hasRole('ADMIN')")
public class VoitureController {
    private final VoitureService voitureService;

    // get all voitures

    @GetMapping("/voitures")
    public ResponseEntity<?> getAllVoitures() {
        try {
            return ResponseEntity.ok( ).body(new MessageResponse(200, "Voitures récupérées avec succès ,Liste des voitures contient " + voitureService.getAllVoitures().size() + " voitures", voitureService.getAllVoitures()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageResponse(500, e.getMessage()));
        }
    }

    @GetMapping("/vims")
    public ResponseEntity<?> getAllVIM() {
        try {
            return ResponseEntity.ok( ).body(new MessageResponse(200, "la recuperation des VIM a été effectuée avec succès ,Liste des VIM contient " + voitureService.getAllVIN().size() + " VIM", voitureService.getAllVIN()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageResponse(500, e.getMessage()));
        }
    }


    @PostMapping("/addVim")
    public ResponseEntity<?> addVim(@Valid @RequestBody String vim, BindingResult result) {
        if (result.hasErrors()) {
          return ResponseEntity.badRequest().body(new MessageResponse(400, "Vim invalide"));
        }
      if (voitureService.addVim(vim)) {
        return ResponseEntity.ok().body(new MessageResponse(200, "Vim ajouté avec succès"));
      } else {
        return ResponseEntity.status(500).body(new MessageResponse(500, "Erreur lors de l'ajout du Vim"));
      }
    }

}
