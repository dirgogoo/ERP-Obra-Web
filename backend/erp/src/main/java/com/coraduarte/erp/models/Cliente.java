package com.coraduarte.erp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table (name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Cliente {

    public interface CreateClient{}
    public interface UpdateClients {}

    public static final String TABLE_NAME = "cliente";

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", unique = true )
    private Long id;

   /*  @Column(name = "password", unique = true, nullable = false)
    @NotNull
    @NotEmpty
    private String password; */
   
    @Column(name = "name", unique = true, nullable = false)
    @NotBlank
    private String name;

    @Column(name = "cnpj", length = 17, nullable = false, unique = true)
    @NotBlank
    private String cnpj;


    
   
    
}
