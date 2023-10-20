package com.dosi.eton.repository;


import com.dosi.eton.models.VIN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VINRepository extends JpaRepository<VIN, Integer> {

}
