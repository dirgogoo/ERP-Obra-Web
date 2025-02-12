package com.coraduarte.erp.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.coraduarte.erp.models.Obras;
import com.coraduarte.erp.models.projection.ObraSearchProjection;


public interface ObraRepository extends JpaRepository<Obras, Long>  {
    Page<ObraSearchProjection> findAllBy(Pageable pageable);
}
