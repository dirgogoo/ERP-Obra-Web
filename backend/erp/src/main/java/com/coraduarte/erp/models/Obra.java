package com.coraduarte.erp.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.coraduarte.erp.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "obras")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Obra {

    public static final String TABLE_NAME = "obras";

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
     @Column(name = "id", unique = true )
    private Long id;

    @Column(name = "nome", nullable = false)
    @NotBlank
    private String nome;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "dataLancamento", nullable = false)
    //@NotBlank
    private String dataLancamento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "dataInicio", nullable = false)
    @NotBlank
    private String dataInicio;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    //@Column(name = "dataPrevista", nullable = false)
    //@NotBlank
    //private String dataPrevista;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
   // @Column(name = "dataTermino", nullable = false)
   // @NotBlank
   // private String dataTermino;

    @Column(name = "status", nullable = false)
    private Integer status;

    //@ManyToOne
    //@JoinColumn(name = "cliente_id", nullable = false)
    //private Cliente cliente;

    @OneToMany(mappedBy = "obra")
    @JsonManagedReference
    private List<EtapaObra> etapa = new ArrayList<EtapaObra>();

    public Status getStatus(){
        return Status.toEnum(status);
    }

    public Status setStatus(Status status){
        this.status = status.getCode();
        return status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, dataLancamento, dataInicio, status);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Obra obra = (Obra) obj;
        return Objects.equals(id, obra.id) &&
               Objects.equals(nome, obra.nome) &&
               Objects.equals(dataLancamento, obra.dataLancamento) &&
               Objects.equals(dataInicio, obra.dataInicio) &&
               Objects.equals(status, obra.status);
    }

}
