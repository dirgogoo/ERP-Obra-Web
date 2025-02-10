package com.coraduarte.erp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coraduarte.erp.models.Etapa;

@Repository
public interface EtapaObraRepository extends JpaRepository<Etapa, Long>{
    
}
