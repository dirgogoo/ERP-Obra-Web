package com.coraduarte.erp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Table (name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity

public class Clients {

    public interface CreateClient{}
    public interface UpdateClients {}

    public static final String TABLE_NAME = "clients";

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", unique = true )
    private Long id;
   
    @Column(name = "name", unique = true, nullable = false)
    @NotNull(groups = CreateClient.class)
    @NotEmpty(groups = CreateClient.class)
    private String name;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "cnpj", length = 17, nullable = false, unique = true)
    @NotNull(groups = {CreateClient.class, UpdateClients.class})
    @NotEmpty(groups = {CreateClient.class, UpdateClients.class})
    private String cnpj;


    
   
    
}
