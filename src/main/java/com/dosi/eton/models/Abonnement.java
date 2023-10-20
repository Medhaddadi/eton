package com.dosi.eton.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Abonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Veuillez spécifier la date de début de l'abonnement")
    private Date dateDebut;

    @NotNull(message = "Veuillez spécifier la date de fin de l'abonnement")
    private Date dateFin;

    @NotNull(message = "Veuillez spécifier l'état de l'abonnement")
    private String etat ;

    @ManyToOne
    @JoinColumn(name = "offre_id")
    @JsonBackReference
    private OffreTarifaire offre;

    @OneToMany(mappedBy = "abonnement", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Facturation> facturations;

    @OneToMany(mappedBy = "abonnement", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DemandeProlongement> demandesProlongement;

    @OneToOne(mappedBy = "abonnement")
    @JsonManagedReference
    private Client client;



    public Abonnement(Date date, Date date1, String enCours) {
        this.dateDebut = date;
        this.dateFin = date1;
        this.etat = enCours;
        this.facturations=new ArrayList<>();
        this.demandesProlongement=new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Abonnement{" +
                "id=" + id +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", etat='" + etat + '\'' +
                ", offre=" + offre +
                ", facturations=" + facturations +
                ", demandesProlongement=" + demandesProlongement +
                ", client=" + client +
                '}';
    }
}
