package com.dosi.eton.services;

import com.dosi.eton.repository.DemandeProlongementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemandeProlongementService {
    private final DemandeProlongementRepository demandeProlongementRepository;


    public void deleteDemandeProlongement(Integer id) {
        demandeProlongementRepository.deleteById(id);
    }
}
