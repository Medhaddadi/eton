package com.dosi.eton.repository;


import com.dosi.eton.models.Carte;
import jakarta.persistence.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteRepository extends JpaRepository<Carte, Integer> {
}
