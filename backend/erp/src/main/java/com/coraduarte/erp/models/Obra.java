package com.coraduarte.erp.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table (name = "obras")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class Obra {

    public static final String TABLE_NAME = "obras";

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
     @Column(name = "id", unique = true )
    private Long id;

    @Column(name = "nome", nullable = false)
    @NotNull
    @NotEmpty
    private String nome;

    @Column(name = "dataLancamento", nullable = false)
    @NotNull
    @NotEmpty
    private Date dataLancamento;

    @Column(name = "dataInicio", nullable = false)
    @NotNull
    @NotEmpty
    private Date dataInicio;

    @Column(name = "dataPrevista", nullable = false)
    @NotNull
    @NotEmpty
    private Date dataPrevista;

    @Column(name = "dataTermino", nullable = false)
    @NotNull
    @NotEmpty
    private Date dataTermino;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "obra")
    private List<EtapaObra> etapa = new ArrayList<EtapaObra>();


}
