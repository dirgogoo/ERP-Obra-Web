package com.coraduarte.erp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table (name = "clients")
public class Clients {

    public interface CreateClient{}
    public interface UpdateClients {}

    public static final String TABLE_NAME = "clients";

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "name", unique = true, nullable = false)
    @NotNull(groups = CreateClient.class)
    @NotEmpty(groups = CreateClient.class)
    private String name;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "cnpj", length = 17, nullable = false, unique = true)
    @NotNull(groups = {CreateClient.class, UpdateClients.class})
    @NotEmpty(groups = {CreateClient.class, UpdateClients.class})
    private String cnpj;


    public Clients() {
    }

    public Clients(String name, String cnpj) {
        this.name = name;
        this.cnpj = cnpj;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Clients name(String name) {
        setName(name);
        return this;
    }

    public Clients cnpj(String cnpj) {
        setCnpj(cnpj);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null)
        return false;
       

        if (!(obj instanceof Clients)) 
            return false;
        
        Clients other = (Clients) obj;
        if (this.name == null)
         if(other.name != null)
          return false;
         else if (!this.name.equals(other.name))
          return false;
        return Objects.equals(this.name, other.name) && Objects.equals(this.cnpj, other.cnpj);
       

    }

    @Override
    public int hashCode() {
       final int prime = 31;
       int result = 1;
       result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
       return result;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", cnpj='" + getCnpj() + "'" +
            "}";
    }
    
}
