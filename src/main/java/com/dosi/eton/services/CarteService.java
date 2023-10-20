package com.dosi.eton.services;


import com.dosi.eton.models.Carte;
import com.dosi.eton.models.Client;
import com.dosi.eton.repository.CarteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CarteService {
    private final CarteRepository carteRepository;


    public void affecterCarte(Client client) {
        try {
            carteRepository.save(new Carte(getNumCarte(), getDateExpiration(),"visa", client));
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'affectation de la carte", e);
        }
    }
    public String getDateExpiration() {
        return new Date(System.currentTimeMillis() + 365 * 24 * 60 * 60 * 1000).toString();
    }

    public String getNumCarte() {
        String numCarte = "";
        for (int i = 0; i < 16; i++) {
            numCarte += (int) (Math.random() * 10);
        }
        return numCarte;
    }
}
