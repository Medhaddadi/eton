package com.dosi.eton.services;



import com.dosi.eton.models.UtilisateurAudi;

import java.util.List;
import java.util.Set;

public interface UtilisateurAudiService {
    Set<UtilisateurAudi> getAllUtilisateurAudi();

    UtilisateurAudi save(UtilisateurAudi utilisateurAudi);

    List<UtilisateurAudi> findByStatut(Boolean statut);

    UtilisateurAudi updateStatut(Long id, Boolean newStatut);
}
