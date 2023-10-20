package com.dosi.eton.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
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

    @OneToMany(mappedBy = "offre", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Abonnement> abonnements;

    @Override
    public String toString() {
        return "OffreTarifaire {" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", fraisMensuels=" + fraisMensuels +
                ", dureeContrat=" + dureeContrat +
                ", fraisChargementAc=" + fraisChargementAc +
                ", fraisChargementDcHpc=" + fraisChargementDcHpc +
                ", partenaireChargeurHautePuissance='" + partenaireChargeurHautePuissance + '\'' +
                '}';
    }
}
