package com.coraduarte.erp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table (name = "itemEtapa")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class itemEtapa {
   
    public static final String TABLE_NAME = "itemEtapa";
 
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", unique = true )
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "dataLancamento", nullable = false)
    //@NotBlank
    private String dataLancamento;

     @ManyToOne
     @JoinColumn(name = "etapa_id", nullable = false)
     @NotNull
     @JsonBackReference
    private EtapaObra etapa;

    @Column(name = "quantidade", nullable = false)
    @NotNull
    private Double quantidade;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false) // Nome da coluna na tabela itemEtapa
    @NotNull
    private Item item;

    @Column(name = "valorTotal", nullable = false)
    private Double valorTotal;


}
