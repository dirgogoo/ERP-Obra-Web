package com.coraduarte.erp.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coraduarte.erp.models.Etapa;

@Repository
public interface EtapaRepository extends JpaRepository<Etapa, Long> {
     Page<Etapa> findByNameContainingIgnoreCase(String nome, Pageable pageable);
}
