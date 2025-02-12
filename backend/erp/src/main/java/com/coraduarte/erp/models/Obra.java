package com.coraduarte.erp.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.coraduarte.erp.models.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "obras")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Obra {

    public static final String TABLE_NAME = "obras";

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
     @Column(name = "id", unique = true )
    private Long id;

    @Column(name = "nome", nullable = false)
    @NotBlank
    private String nome;

    @Column(name = "dataLancamento", nullable = false)
    @NotBlank
    private Date dataLancamento;

    @Column(name = "dataInicio", nullable = false)
    @NotBlank
    private Date dataInicio;

    @Column(name = "dataPrevista", nullable = false)
    @NotBlank
    private Date dataPrevista;

    @Column(name = "dataTermino", nullable = false)
    @NotBlank
    private Date dataTermino;

    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "obra")
    private List<EtapaObra> etapa = new ArrayList<EtapaObra>();


}
