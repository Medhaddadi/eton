package com.dosi.eton.repository;



import com.dosi.eton.models.UtilisateurAudi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface UtilisateurAudiRepository extends JpaRepository<UtilisateurAudi, Long> {
    List<UtilisateurAudi> findByStatut(Boolean statut);
}
