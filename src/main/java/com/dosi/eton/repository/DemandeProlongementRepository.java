package com.dosi.eton.repository;

import com.dosi.eton.models.DemandeProlongement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeProlongementRepository extends JpaRepository<DemandeProlongement, Integer> {
}
