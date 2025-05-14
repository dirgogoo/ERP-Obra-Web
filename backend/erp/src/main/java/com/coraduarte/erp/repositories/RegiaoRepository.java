package com.coraduarte.erp.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coraduarte.erp.models.Regiao;
import com.coraduarte.erp.models.projection.ObraSearchProjection;

@Repository
public interface RegiaoRepository extends JpaRepository<Regiao, Long>{
    Page<Regiao> findAllBy(Pageable pageable);
    Page<Regiao> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
