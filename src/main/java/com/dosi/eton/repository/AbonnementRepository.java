package com.dosi.eton.repository;

import com.dosi.eton.models.Abonnement;
import com.dosi.eton.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {

}
