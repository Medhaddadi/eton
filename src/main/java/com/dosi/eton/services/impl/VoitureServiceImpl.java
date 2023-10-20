package com.dosi.eton.services.impl;


import com.dosi.eton.models.VIN;
import com.dosi.eton.models.Voiture;
import com.dosi.eton.repository.VINRepository;
import com.dosi.eton.repository.VoitureRepository;
import com.dosi.eton.services.VoitureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class VoitureServiceImpl implements VoitureService {
    Logger logger = Logger.getLogger(VoitureServiceImpl.class.getName());

    private final VoitureRepository voitureRepository;
    private final VINRepository vinRepository;


    @Override
    public Voiture save(Voiture car) throws Exception {
        logger.info("Enregistrement de la voiture");
        if (voitureRepository.existsByVin(car.getVin())) {
            throw new Exception("La voiture avec VIN " + car.getVin() + " existe déjà");
        }
        VIN existingCar = voitureRepository.findByVINNumber(car.getVin());
        if (existingCar == null) {
            throw new Exception("La voiture avec VIN  " + car.getVin() + " n'exit pas dans notre base de données");
        }

        try {
            return voitureRepository.save(car);
        } catch (Exception e) {
            throw new Exception("Erreur lors de l'enregistrement de la voiture", e);
        }
    }

    @Override
    public List<Voiture> getAllVoitures() {
        return voitureRepository.findAll();
    }

    @Override
    public List<VIN> getAllVIN() {
        return vinRepository.findAll();
    }

    @Override
    public boolean addVim(String vim) {
        if (voitureRepository.existsByVin(vim)) {
            return false;
        }
        VIN vin = new VIN();
        vin.setVin_number(vim);
        vinRepository.save(vin);
        return true;
    }


}
