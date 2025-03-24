package com.coraduarte.erp.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coraduarte.erp.models.ItemEtapa;

@Repository
public interface ItemEtapaRepository extends JpaRepository<ItemEtapa, Long> {
    Page<ItemEtapa> findAllByEtapaObra_Id(Long id,Pageable pageable);

    List<ItemEtapa> findAllByEtapaObra_Id(Long id);
    
}
