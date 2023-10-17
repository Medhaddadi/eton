package com.dosi.eton.models;

import com.dosi.eton.user.Role;
import com.dosi.eton.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@PrimaryKeyJoinColumn(name = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Client extends User {

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

     

    public Client(String firstname, String lastname, String email, String adresse, String telephone, String encode, Role role) {
        super(firstname, lastname, email, adresse, telephone, encode, role);

    }
}
