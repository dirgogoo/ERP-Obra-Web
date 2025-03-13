package com.coraduarte.erp.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.coraduarte.erp.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table (name = "etapaObra")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class EtapaObra {

    public static final String TABLE_NAME = "etapaObra";

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", unique = true )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "obra_id", nullable = false) // Definindo chave estrangeira corretamente
    @NotNull
    @JsonBackReference
    private Obra obra;

    @Column(name = "price", nullable = false)
    @NotNull
    private Long price;

    @Column(name = "deadline", nullable = false)
    @NotNull
    private String deadline;

    @Column(name = "status", nullable = false)
    private Integer status;

   @OneToMany
   @JsonManagedReference
    private List<itemEtapa> itens = new ArrayList<itemEtapa>();

    @ManyToOne
    @JoinColumn(name = "etapa_id", nullable = false)
    private Etapa etapa;

    public Status getStatus(){
        return Status.toEnum(status);
    }

    public Status setStatus(Status status){
        this.status = status.getCode();
        return status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, deadline, status);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EtapaObra etapaObra = (EtapaObra) obj;
        return Objects.equals(id, etapaObra.id) &&
               Objects.equals(price, etapaObra.price) &&
               Objects.equals(deadline, etapaObra.deadline) &&
               Objects.equals(status, etapaObra.status);
    }
}
