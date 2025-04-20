package com.coraduarte.erp.models;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import java.util.Objects;


public class Regiao {
     public static final String TABLE_NAME = "regiao";

     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Id
     @Column(name = "id", unique = true )
     private Long id;

     @Column(name = "nome", unique = true)
     @NotEmpty
     @NotBlank
     private String nome;



     public Regiao() {
     }

     public Regiao(Long id, String nome) {
          this.id = id;
          this.nome = nome;
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

     public Regiao id(Long id) {
          setId(id);
          return this;
     }

     public Regiao nome(String nome) {
          setNome(nome);
          return this;
     }

     @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Regiao)) {
            return false;
        }
        Regiao regiao = (Regiao) o;
        return Objects.equals(id, regiao.id) && Objects.equals(nome, regiao.nome);
     }

     @Override
     public int hashCode() {
          return Objects.hash(id, nome);
     }

     @Override
     public String toString() {
          return "{" +
               " id='" + getId() + "'" +
               ", nome='" + getNome() + "'" +
               "}";
     }

}
