package com.dosi.eton.services;


import com.dosi.eton.models.Voiture;

public interface VoitureService {

    // create voiture


    boolean ImatriculationExist(String imatriculation);

    Voiture save(Voiture car);
}
