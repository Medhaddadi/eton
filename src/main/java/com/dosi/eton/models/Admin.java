package com.dosi.eton.models;

import com.dosi.eton.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "adminstarteur")
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {

    @NotEmpty(message = "Veuillez saisir votre nom")
    @NotNull(message = "Veuillez saisir votre nom")
    @Size(min = 2, max = 30, message = "Veuillez saisir un nom valide")
    private String nom;

    @NotEmpty(message = "Veuillez saisir votre prenom")
    @NotNull(message = "Veuillez saisir votre prenom")
    @Size(min = 2, max = 30, message = "Veuillez saisir un nom valide")
    private String prenom;

    @NotEmpty(message = "Veuillez saisir votre poste")
    @NotNull(message = "Veuillez saisir votre poste")
    private String poste;

    @NotEmpty(message = "Veuillez saisir votre  adresse")
    @NotNull(message = "Veuillez saisir votre  adresse")
    @Size(min = 2, max = 70, message = "Veuillez saisir un nom valide")
    private String adresse;

    private String telephone;


}
