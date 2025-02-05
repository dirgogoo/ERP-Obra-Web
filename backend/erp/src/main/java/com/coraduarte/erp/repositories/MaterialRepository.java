package com.coraduarte.erp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coraduarte.erp.models.Materiais;

public interface MaterialRepository  extends JpaRepository<Materiais, Long> {
    

}
