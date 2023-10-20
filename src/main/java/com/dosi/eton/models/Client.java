package com.dosi.eton.models;

import com.dosi.eton.user.Role;
import com.dosi.eton.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@PrimaryKeyJoinColumn(name = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Client extends User {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "voiture_id", referencedColumnName = "id")
    private Voiture voiture;


    @OneToOne
    @JoinColumn(name = "abonnement_id")
    @JsonBackReference
    private Abonnement abonnement;

    public Client(String firstname, String lastname, String email, String adresse, String telephone, String encode, Role role) {
        super(firstname, lastname, email, adresse, telephone, encode, role);
    }

    @Override
    public String toString() {
        return "Client{" +
                "voiture=" + voiture +
                '}';
    }
}
