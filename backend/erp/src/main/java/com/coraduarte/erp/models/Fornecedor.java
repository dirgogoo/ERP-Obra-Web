package com.coraduarte.erp.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.Objects;

public class Fornecedor{
    public static final String TABLE_NAME = "fornecedor";

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", unique = true )
    private Long id;

    @Column(name = "nome", unique = true )
    @NotEmpty
    @NotBlank
    public String nome;

    @Column(name = "regiao", unique = true)
    @NotEmpty
    @NotBlank
    public Regiao regiao;
   

    public Fornecedor() {
    }

    public Fornecedor(Long id, String nome, Regiao regiao) {
        this.id = id;
        this.nome = nome;
        this.regiao = regiao;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Regiao getRegiao() {
        return this.regiao;
    }

    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }

    public Fornecedor id(Long id) {
        setId(id);
        return this;
    }

    public Fornecedor nome(String nome) {
        setNome(nome);
        return this;
    }

    public Fornecedor regiao(Regiao regiao) {
        setRegiao(regiao);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Fornecedor)) {
            return false;
        }
        Fornecedor fornecedor = (Fornecedor) o;
        return Objects.equals(id, fornecedor.id) && Objects.equals(nome, fornecedor.nome) && Objects.equals(regiao, fornecedor.regiao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, regiao);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", regiao='" + getRegiao() + "'" +
            "}";
    }
}
    
