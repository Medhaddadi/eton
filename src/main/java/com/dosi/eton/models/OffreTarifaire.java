package com.dosi.eton.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import java.util.Date;

@Entity
@Table(name = "offres_tarifaires")
public class OffreTarifaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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


    // created by an admin
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "created_by", nullable = false)
    @CreatedBy
    private String createdBy;
    private Date deleted_at;
}
