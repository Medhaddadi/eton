package com.dosi.eton.repository;

import com.dosi.eton.models.OffreTarifaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OffreTarifaireRepository extends JpaRepository<OffreTarifaire, Integer> {
    @Query("SELECT o FROM OffreTarifaire o WHERE o.nom LIKE %?1%")
    List<OffreTarifaire> findByNomContaining(String nom);

    Optional<Object> findByNom(String nom);

    @Query("SELECT o FROM OffreTarifaire o WHERE o.nom LIKE %?1%")
    List<OffreTarifaire> searchOffre(String query);
}
