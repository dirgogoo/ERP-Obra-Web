package com.coraduarte.erp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coraduarte.erp.models.Servico;

@Repository
public interface ServicosRepository  extends JpaRepository<Servico, Long>{
   
    

    
}
