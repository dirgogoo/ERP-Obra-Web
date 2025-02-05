package com.coraduarte.erp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coraduarte.erp.models.Etapas;

@Repository
public interface EtapasRepository extends JpaRepository<Etapas, Long> {
    
}
