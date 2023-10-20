package com.dosi.eton.repository;


import com.dosi.eton.models.Facturation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturationRepository extends JpaRepository<Facturation, Integer> {
}
