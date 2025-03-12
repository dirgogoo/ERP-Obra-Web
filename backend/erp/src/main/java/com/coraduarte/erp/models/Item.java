package com.coraduarte.erp.models;

import com.coraduarte.erp.models.enums.tipoItem;

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
@Table (name = "item")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Item {

    public static final String TABLE_NAME = "item";
 
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", unique = true )
    private Long id;

    @Column(name = "nome", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "valor", nullable = false)
    @NotNull
    private Double valor;

    @Column(name = "unidade", nullable = false)
    @NotBlank
    private String unidade;

    @Column(name = "tipo", nullable = false)
    @NotNull
    private Integer tipo;

    public tipoItem getTipo() {
        return tipoItem.toEnum(tipo);
    }

    public void setTipo(tipoItem tipo) {
        this.tipo = tipo.getCode();
    }


}
