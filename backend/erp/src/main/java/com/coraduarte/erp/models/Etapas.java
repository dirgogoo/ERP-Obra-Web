package com.coraduarte.erp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Column;


@Entity
@Table(name = Etapas.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class Etapas {
    public static final String TABLE_NAME = "stages";

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", unique = true )
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull
    @NotEmpty
    private String name;


    @Column(name = "price", nullable = false)
    @NotNull
    @NotEmpty
    private Long price;

    @Column(name = "deadline", nullable = false)
    @NotNull
    @NotEmpty
    private Integer deadline;

}
