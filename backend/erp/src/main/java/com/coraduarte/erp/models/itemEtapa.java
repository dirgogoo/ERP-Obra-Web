package com.coraduarte.erp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table (name = "itemEtapa")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ItemEtapa {
   
    public static final String TABLE_NAME = "itemEtapa";
 
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", unique = true )
    private Long id;

     @ManyToOne
     @JoinColumn(name = "etapa_id", nullable = false)
     @NotNull
    private EtapaObra etapa;

    @Column(name = "quantidade", nullable = false)
    @NotBlank
    private Double quantidade;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false) // Nome da coluna na tabela itemEtapa
    @NotNull
    private Item item;

    @Column(name = "valorTotal", nullable = false)
    @NotNull
    private Double valorTotal;


}
