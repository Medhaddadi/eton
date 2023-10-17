package com.dosi.eton.Dto;

import com.dosi.eton.models.STATUS;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class ClientRegistrationRequest {

    @NotEmpty(message = "First name cannot be empty")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstname;

    @NotEmpty(message = "Last name cannot be empty")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastname;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters")
    private String password;

    @NotEmpty(message = "Telephone cannot be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Invalid telephone number format")
    private String telephone;

    @NotEmpty(message = "Address cannot be empty")
    @Size(min = 2, max = 100, message = "Address must be between 2 and 100 characters")
    private String adresse;


    private String modele;

    private String annee;

    private String kilometrage;

    @NotEmpty(message = "Immatriculation cannot be empty")
    private String immatriculation;
}