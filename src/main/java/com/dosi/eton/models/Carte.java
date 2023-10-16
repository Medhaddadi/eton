package com.dosi.eton.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Carte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero", unique = true)
    private String numero;
    private String dateExpiration;
    private String type;

    @OneToOne
    @JoinColumn(name = "utilisateurAudi_id")
    private UtilisateurAudi utilisateurAudi;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "created_by", nullable = false)
    @CreatedBy
    private String createdBy;

    private Date deleted_at;

}
