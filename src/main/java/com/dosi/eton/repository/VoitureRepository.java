package com.dosi.eton.repository;

import com.dosi.eton.models.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface VoitureRepository extends JpaRepository<Voiture, Long> {

    boolean existsByImatriculation(String imatriculation);
}
