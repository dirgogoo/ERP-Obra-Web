package com.coraduarte.erp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "regiao")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Regiao {
     public static final String TABLE_NAME = "regiao";

     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Id
     @Column(name = "id", unique = true )
     private Long id;

     @Column(name = "nome", unique = true)
     @NotEmpty
     @NotBlank
     private String nome;

}
