package com.dosi.eton.services.impl;

import com.dosi.eton.models.UtilisateurAudi;
import com.dosi.eton.repository.UtilisateurAudiRepository;
import com.dosi.eton.services.UtilisateurAudiService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UtilisateurAudiServiceimpl implements UtilisateurAudiService {

    private final UtilisateurAudiRepository utilisateurAudiRepository;

    public UtilisateurAudiServiceimpl(UtilisateurAudiRepository utilisateurAudiRepository) {
        this.utilisateurAudiRepository = utilisateurAudiRepository;
    }
    @Override
    public Set<UtilisateurAudi> getAllUtilisateurAudi() {
        return new HashSet<>(utilisateurAudiRepository.findAll());
    }

    @Override
    public UtilisateurAudi save(UtilisateurAudi utilisateurAudi) {
        return utilisateurAudiRepository.save(utilisateurAudi);
    }
    public List<UtilisateurAudi> findByStatut(Boolean statut) {
        return utilisateurAudiRepository.findByStatut(statut);
    }
    public UtilisateurAudi updateStatut(Long id, Boolean newStatut) {
        UtilisateurAudi user = utilisateurAudiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        user.setStatut(newStatut);
        return utilisateurAudiRepository.save(user);
    }

}
