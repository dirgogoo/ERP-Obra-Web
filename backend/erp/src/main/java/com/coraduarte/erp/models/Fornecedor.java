package com.coraduarte.erp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "fornecedor")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Fornecedor{
    public static final String TABLE_NAME = "fornecedor";

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", unique = true )
    private Long id;

    @Column(name = "nome") // Allow duplicate 'nome' values
    @NotEmpty
    @NotBlank
    public String nome;

    @Column(name = "cnpj", unique = true, nullable = false) // Ensure 'cnpj' is unique and not null
    @NotEmpty
    @NotBlank
    public String cnpj;

    @ManyToOne
    @JoinColumn(name = "regiao_id", nullable = false) // Ensure 'unique' is set to false to allow multiple fornecedores to share the same regiao
    private Regiao regiao; // Ensure Regiao class is defined and imported
}
    
