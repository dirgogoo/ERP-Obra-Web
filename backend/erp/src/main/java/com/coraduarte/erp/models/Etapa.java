package com.coraduarte.erp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = Etapa.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Etapa {
    public static final String TABLE_NAME = "stages";

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", unique = true )
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;


    @Column(name = "price", nullable = false)
    @NotNull
    private Long price;

    @Column(name = "deadline", nullable = false)
    @NotNull
    private Integer deadline;

}
