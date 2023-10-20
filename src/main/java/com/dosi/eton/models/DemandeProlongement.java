package com.dosi.eton.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
public class DemandeProlongement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "abonnement_id")
    @JsonBackReference
    private Abonnement abonnement;

    private String raison;


    @Override
    public String toString() {
        return "DemandeProlongement{" +
                "id=" + id +
                ", raison='" + raison + '\'' +
                '}';
    }
}
