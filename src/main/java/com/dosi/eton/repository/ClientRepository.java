package com.dosi.eton.repository;



import com.dosi.eton.models.Client;
import com.dosi.eton.models.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
