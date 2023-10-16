package com.dosi.eton.models;

import com.dosi.eton.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@PrimaryKeyJoinColumn(name = "id")
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurAudi extends User {

    @NotEmpty(message = "Veuillez saisir votre nom")
    @NotNull(message = "Veuillez saisir votre nom")
    @Size(min = 2, max = 30, message = "Veuillez saisir un nom valide")
    private String nom;

    @NotEmpty(message = "Veuillez saisir votre prenom")
    @NotNull(message = "Veuillez saisir votre prenom")
    @Size(min = 2, max = 30, message = "Veuillez saisir un nom valide")
    private String prenom;

    @NotEmpty(message = "Veuillez saisir votre  adresse")
    @NotNull(message = "Veuillez saisir votre  adresse")
    @Size(min = 2, max = 70, message = "Veuillez saisir un nom valide")
    private String adresse;

    @NotEmpty(message = "Veuillez saisir votre telephone")
    @NotNull(message = "Veuillez saisir votre telephone")
    // telephone validation regex
    @Column(name = "telephone", unique = true)
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Veuillez saisir un numero de telephone valide")
    @Size(min = 2, max = 30, message = "Veuillez saisir un nom valide")
    private String telephone;

    @NotNull(message = "Veuillez saisir votre numero de chassis")
    private Boolean statut = false;

    @OneToMany
    @JoinColumn(name = "utilisateur_id")
    private Set<Facturation> facturations;

    @OneToMany
    @JoinColumn(name = "utilisateur_id")
    private Set<DemandeProlongement> demandesProlongement;


    @OneToMany
    @JoinColumn(name = "utilisateur_id")
    private Set<Abonnement> abonnements;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "voiture_id", referencedColumnName = "id")
    private Voiture voiture;


}
