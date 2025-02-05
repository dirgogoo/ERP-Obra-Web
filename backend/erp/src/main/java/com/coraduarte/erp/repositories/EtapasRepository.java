package com.coraduarte.erp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coraduarte.erp.models.Etapas;

public interface EtapasRepository extends JpaRepository<Etapas, Long> {
    @Query(value = "SELECT * FROM etapas e WHERE e.cliente_id = :id", nativeQuery = true)
    List<Etapas> findByCliente_Id(@Param("id") Long id);

    void saveAll(String name, Long id, String cnpj);
}
