package com.dosi.eton.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class Facturation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date DatePaymant;

    @NotNull(message = "Veuillez spécifier le montant de paymant")
    private Double MontantPaymant;

    private String TypePaymant;

    private String etat;

    @ManyToOne
    @JoinColumn(name = "abonnement_id")
    @JsonBackReference
    private Abonnement abonnement;



    public Facturation(String enCours, Double fraisMensuels) {
        this.etat = enCours;
        this.MontantPaymant = fraisMensuels;
    }

    @Override
    public String toString() {
        return "Facturation{" +
                "id=" + id +
                ", DatePaymant=" + DatePaymant +
                ", MontantPaymant=" + MontantPaymant +
                ", TypePaymant='" + TypePaymant + '\'' +
                ", etat='" + etat + '\'' +
                '}';
    }
}
