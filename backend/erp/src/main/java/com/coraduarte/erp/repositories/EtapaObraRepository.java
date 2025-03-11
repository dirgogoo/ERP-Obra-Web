package com.coraduarte.erp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coraduarte.erp.models.EtapaObra;

@Repository
public interface EtapaObraRepository extends JpaRepository<EtapaObra, Long>{
    List<EtapaObra> findAllByObra_Id(Long id);
    
}
