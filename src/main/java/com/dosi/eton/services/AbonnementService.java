package com.dosi.eton.services;

import com.dosi.eton.models.*;

import com.dosi.eton.repository.AbonnementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AbonnementService {

    private final AbonnementRepository abonnementRepository;

    private final OffreTarifaireService offreTarifaireService;

    private final ClientService clientService;
    private final FacturationService facturationService;
    private final DemandeProlongementService demandeProlongementService;

    Logger logger = Logger.getLogger(AbonnementService.class.getName());

    public Optional<Abonnement> getAbonnementById(Integer id) {
        return abonnementRepository.findById(id);
    }


    @Transactional
    public void souscrire(Integer idOffre, Integer idClient ) {

        Client client = clientService.findById(Long.valueOf(idClient));
        if (client.getAbonnement() != null) {
            throw new RuntimeException("Vous avez déjà un abonnement");
        }

        OffreTarifaire offreTarifaire = offreTarifaireService.findById(idOffre);

        Abonnement abonnement = new Abonnement(new java.util.Date(), new java.util.Date(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000), "En cours");
        abonnement.setOffre(offreTarifaire);
        abonnement.setClient(client);
        Facturation facturation = null;
        try {
            facturation=  facturationService.saveFacturation(new Facturation("En cours",  offreTarifaire.getFraisMensuels()));
        } catch (Exception e) {
            throw new RuntimeException(" Erreur lors de la création de la facturation", e);
        }
        facturation.setAbonnement(abonnement);
        abonnement.getFacturations().add(facturation);
        client.setAbonnement(abonnement);

        abonnementRepository.save(abonnement);
    }

    public Abonnement save(Abonnement abonnement) {
        logger.info("calling save abonnement ..."+abonnement );
        try {
            return abonnementRepository.save(abonnement);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création de l'abonnement", e);
        }
    }

    @Transactional
    public void deleteAbonnement(Integer id) {
        Optional<Abonnement> abonnementOptional = abonnementRepository.findById(id);
        if (!abonnementOptional.isPresent()) {
            throw new RuntimeException("Abonnement with ID " + id + " not found");
        }

        Abonnement abonnement = abonnementOptional.get();

        // Remove associations to prevent foreign key violations
        if (abonnement.getClient() != null) {
            abonnement.getClient().setAbonnement(null);
        }

        if (abonnement.getFacturations() != null) {
            abonnement.getFacturations().clear();
        }

        if (abonnement.getDemandesProlongement() != null) {
            abonnement.getDemandesProlongement().clear();
        }

        abonnementRepository.delete(abonnement);
    }


    public List<Abonnement> getAllAbonnements() {
        if (abonnementRepository.findAll().isEmpty()) {
            throw new RuntimeException("Aucun abonnement trouvé");
        }
        return abonnementRepository.findAll();
    }
}
