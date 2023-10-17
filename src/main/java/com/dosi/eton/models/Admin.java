package com.dosi.eton.models;

import com.dosi.eton.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "adminstarteur")
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {

    @NotEmpty(message = "Veuillez saisir votre poste")
    @NotNull(message = "Veuillez saisir votre poste")
    @Size(min = 2, max = 30, message = "Veuillez saisir un nom valide")
    private String poste;


     

}
