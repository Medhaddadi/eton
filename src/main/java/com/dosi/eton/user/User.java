package com.dosi.eton.user;

import com.dosi.eton.token.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {

  @Id
  @GeneratedValue
  private Integer id;
  private String firstname;
  private String lastname;
  private String email;
  @Column(name = "telephone")
  @Pattern(regexp = "(^$|[0-9]{10})", message = "Veuillez saisir un numero de telephone valide")
  @Size(min = 2, max = 30, message = "Veuillez saisir un nom valide")
  private String telephone;
  private String password;
  @NotEmpty(message = "Veuillez saisir votre  adresse")
  @NotNull(message = "Veuillez saisir votre  adresse")
  @Size(min = 2, max = 70, message = "Veuillez saisir un nom valide")
  private String adresse;


  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany(mappedBy = "user")
  private List<Token> tokens;

  public User(String firstname, String lastname, String email, String adresse, String telephone, String encode, Role role) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.adresse = adresse;
    this.telephone = telephone;
    this.password = encode;
    this.role = role;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
