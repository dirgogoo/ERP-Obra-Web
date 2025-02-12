package com.coraduarte.erp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coraduarte.erp.models.Material;

public interface MaterialRepository  extends JpaRepository<Material, Long> {
    

}
