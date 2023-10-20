package com.dosi.eton.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Carte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero", unique = true)
    private String numero;
    private String dateExpiration;
    private String type;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Carte(String numCarte, String dateExpiration, String visa, Client client) {
        this.numero = numCarte;
        this.dateExpiration = dateExpiration;
        this.type = visa;
        this.client = client;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", dateExpiration='" + dateExpiration + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
