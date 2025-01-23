package com.coraduarte.erp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coraduarte.erp.models.Materiais;

public interface MateriaisRepository  extends JpaRepository<Materiais, Long> {
    
    @Query(value = "SELECT * FROM materiais m WHERE m.cliente_id = :id", nativeQuery = true)
    List<Materiais> findByCliente_Id(@Param("id") Long id);
}
