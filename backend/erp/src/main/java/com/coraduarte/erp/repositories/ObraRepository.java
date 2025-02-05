package com.coraduarte.erp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coraduarte.erp.models.Obras;

public interface ObraRepository extends JpaRepository<Obras, Long> {
    
}
