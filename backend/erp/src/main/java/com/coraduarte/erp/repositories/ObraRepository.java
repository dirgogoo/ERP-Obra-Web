package com.coraduarte.erp.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coraduarte.erp.models.Obra;
import com.coraduarte.erp.models.projection.ObraSearchProjection;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Long>  {
    Page<ObraSearchProjection> findAllBy(Pageable pageable);
    Page<ObraSearchProjection> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
