package com.dosi.eton.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@RequiredArgsConstructor
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String modele;
    private String annee;
    private String kilometrage;

    // numéro de châssis de voiture". Le numéro de châssis (ou numéro de VIN - Vehicle Identification Number) est un code unique utilisé par l'industrie automobile pour identifier chaque véhicule produit.
    @NotEmpty(message = "Veuillez saisir votre VIN")
    @NotNull(message = "Veuillez saisir votre VIN")
    @Column(name = "VIN", unique = true)
    private String vin ;


    public Voiture(String modele, String annee, String kilometrage, String VIN) {
        this.modele = modele;
        this.annee = annee;
        this.kilometrage = kilometrage;
        this.vin = VIN;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "id=" + id +
                ", modele='" + modele + '\'' +
                ", annee='" + annee + '\'' +
                ", kilometrage='" + kilometrage + '\'' +
                ", vin='" + vin + '\'' +
                '}';
    }
}
