package com.dosi.eton.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modele;
    private String annee;
    private String kilometrage;

    @NotEmpty(message = "Veuillez saisir votre immatriculation")
    @NotNull(message = "Veuillez saisir votre immatriculation")
    @Column(name = "immatriculation", unique = true)
    private String imatriculation;



}
