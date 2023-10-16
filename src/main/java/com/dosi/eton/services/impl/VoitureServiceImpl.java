package com.dosi.eton.services.impl;


import com.dosi.eton.models.Voiture;
import com.dosi.eton.repository.VoitureRepository;
import com.dosi.eton.services.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoitureServiceImpl implements VoitureService {

    private VoitureRepository voitureRepository;

    @Autowired
    public VoitureServiceImpl(  VoitureRepository voitureRepository) {
        this.voitureRepository = voitureRepository;
    }

    @Override
    public boolean ImatriculationExist(String imatriculation) {
        return  voitureRepository.existsByImatriculation(imatriculation);
    }

    @Override
    public Voiture save(Voiture car) {

        return voitureRepository.save(car);
    }


}
