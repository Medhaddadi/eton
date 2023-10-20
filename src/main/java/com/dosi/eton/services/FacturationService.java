package com.dosi.eton.services;


import com.dosi.eton.models.Abonnement;
import com.dosi.eton.models.Client;
import com.dosi.eton.models.Facturation;
import com.dosi.eton.repository.FacturationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacturationService {
    private final FacturationRepository facturationRepository;

    public Facturation getFacturationById(Integer idFacture) {
        return facturationRepository.findById(idFacture).get();
    }

    public Facturation saveFacturation(Facturation facturation) {
        return facturationRepository.save(facturation);
    }


    public void payerFacture(Integer idfacture, Client client) {
        Abonnement abonnement = client.getAbonnement();
        Facturation facturation = abonnement.getFacturations()
                .stream()
                .filter(fact -> fact.getId().equals(idfacture))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Facture not found for ID: " + idfacture));
        if (facturation != null) {
            if (facturation.getEtat().equals("Payée")) {
                throw new RuntimeException("Cette facture est déjà payée");
            }
            facturation.setEtat("Payée");
            facturation.setTypePaymant("cash");
            facturation.setDatePaymant(new java.util.Date());
            facturationRepository.save(facturation);

        } else {
            throw new RuntimeException("Facture not found for ID: " + idfacture);
        }

    }
}
