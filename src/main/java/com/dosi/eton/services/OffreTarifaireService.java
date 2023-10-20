package com.dosi.eton.services;

import com.dosi.eton.models.OffreTarifaire;
import com.dosi.eton.repository.OffreTarifaireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OffreTarifaireService {

    private final OffreTarifaireRepository offreTarifaireRepository;


    public List<OffreTarifaire> getAllOffresTarifaires() {
        if (offreTarifaireRepository.findAll().isEmpty()) {
            throw new RuntimeException("Aucune offre tarifaire trouvée");
        }
        return offreTarifaireRepository.findAll();
    }

    public OffreTarifaire save(OffreTarifaire offre) {

        offreTarifaireRepository.findByNom(offre.getNom())
                .ifPresent(o -> {
                    throw new RuntimeException("Le nom de l'offre tarifaire est déjà utilisé");
                });

        try {
            return offreTarifaireRepository.save(offre);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création de l'offre tarifaire", e);
        }
    }

    public OffreTarifaire findById(Integer id) {

        try {
            return offreTarifaireRepository.findById(id).get();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de l'offre tarifaire", e);
        }
    }

    public boolean existsById(Integer id) {
        if (id == null) {
            throw new RuntimeException("Veuillez spécifier l'id de l'offre tarifaire");
        }
        try {
            return offreTarifaireRepository.existsById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la vérification de l'existence de l'offre tarifaire", e);
        }
    }

    public void deleteById(Integer id) {
        try {
            offreTarifaireRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression de l'offre tarifaire", e);
        }
    }

    public List<OffreTarifaire> findByNomContaining(String nom) {
        if (nom == null) {
            throw new RuntimeException("Veuillez spécifier le nom de l'offre tarifaire");
        }
        try {
            return offreTarifaireRepository.findByNomContaining(nom);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de l'offre tarifaire", e);
        }
    }

    public List<OffreTarifaire> searchOffre(String query) {
        if (query == null) {
            throw new RuntimeException("Veuillez spécifier le nom de l'offre tarifaire");
        }
        try {
            return offreTarifaireRepository.searchOffre(query);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la recherche de l'offre tarifaire", e);
        }
    }
}
