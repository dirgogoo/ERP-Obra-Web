package com.coraduarte.erp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coraduarte.erp.models.Materiais;

public interface MateriaisRepository  extends JpaRepository<Materiais, Long> {
    

}
