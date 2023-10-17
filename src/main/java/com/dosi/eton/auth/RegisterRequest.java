package com.dosi.eton.auth;

import com.dosi.eton.user.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String firstname;
  private String lastname;
  private String email;
  @Size(min = 8, max = 30, message = "Veuillez saisir un mot de passe valide")
  @NotEmpty(message = "Veuillez saisir votre mot de passe")
  @NotNull(message = "Veuillez saisir votre mot de passe")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Veuillez saisir un mot de passe  de 8 caractères minimum avec au moins une majuscule, une minuscule et un chiffre")
  private String password;
  @Column(name = "telephone")
  @Pattern(regexp = "(^$|[0-9]{10})", message = "Veuillez saisir un numero de telephone valide")
  @Size(min = 2, max = 30, message = "Veuillez saisir un nom valide")
  private String telephone;
  @NotEmpty(message = "Veuillez saisir votre  adresse")
  @NotNull(message = "Veuillez saisir votre  adresse")
  @Size(min = 2, max = 70, message = "Veuillez saisir un nom valide")
  private String adresse;
  private Role role;
}
