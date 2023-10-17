package com.dosi.eton.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "offres_tarifaires")
public class OffreTarifaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Veuillez spécifier le nom de l'offre tarifaire")
    private String nom;

    @NotNull(message = "Veuillez spécifier le coût mensuel de l'offre")
    private Double fraisMensuels;

    @NotNull(message = "Veuillez spécifier la durée du contrat en mois")
    private Integer dureeContrat;

    private Double fraisChargementAc;
    private Double fraisChargementDcHpc;

    private String partenaireChargeurHautePuissance;
    private Double fraisBlocage;


     
}
