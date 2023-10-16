package com.dosi.eton.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import java.util.Date;
import java.util.Set;
@Data
@Entity
@Table(name = "adminstarteur")
public class Abonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Veuillez spécifier la date de début de l'abonnement")
    private Date dateDebut;

    @NotNull(message = "Veuillez spécifier la date de fin de l'abonnement")
    private Date dateFin;

    @NotNull(message = "Veuillez spécifier l'état de l'abonnement")
    private Boolean etat = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "offre_id")
    private OffreTarifaire offre ;

    @OneToMany
    @JoinColumn(name = "abonnement_id")
    private Set<Facturation> facturations;

    @OneToMany
    @JoinColumn(name = "abonnement_id")
    private Set<DemandeProlongement> demandesProlongement;

    @OneToMany
    @JoinColumn(name = "abonnement_id")
    private Set<UtilisateurAudi> utilisateurAudis;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "created_by", nullable = false)
    @CreatedBy
    private String createdBy;

    private Date deleted_at;

}
