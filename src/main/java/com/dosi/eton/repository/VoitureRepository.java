package com.dosi.eton.repository;

import com.dosi.eton.models.VIN;
import com.dosi.eton.models.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public
interface VoitureRepository extends JpaRepository<Voiture, Long> {


    @Query("SELECT v FROM VIN v WHERE v.vin_number = ?1")
    VIN findByVINNumber(String vin);

    boolean existsByVin(String vin);
}
