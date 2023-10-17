package com.dosi.eton.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
@Data
@Entity
public class Abonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
    private Set<Client> clients;


}
