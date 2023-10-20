package com.dosi.eton.services;


import com.dosi.eton.models.VIN;
import com.dosi.eton.models.Voiture;

import java.util.List;

public interface VoitureService {

    // create voiture



    Voiture save(Voiture car) throws Exception;

    List<Voiture> getAllVoitures();

    List<VIN> getAllVIN();

    boolean addVim(String vim);
}
