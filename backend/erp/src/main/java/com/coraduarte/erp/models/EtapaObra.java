package com.coraduarte.erp.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table (name = "etapaObra")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class EtapaObra {

    public static final String TABLE_NAME = "etapaObra";

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", unique = true )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "obra_id", nullable = false) // Definindo chave estrangeira corretamente
    @NotNull
    private Obras obra;


   @OneToMany
    private List<ItemEtapa> itens = new ArrayList<ItemEtapa>();

    @ManyToOne
    @JoinColumn(name = "etapa_id", nullable = false)
    private Etapas etapa;
}
