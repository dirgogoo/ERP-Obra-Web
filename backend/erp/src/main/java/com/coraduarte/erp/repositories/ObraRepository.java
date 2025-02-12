package com.coraduarte.erp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coraduarte.erp.models.Obra;

public interface ObraRepository extends JpaRepository<Obra, Long>  {
    
}
