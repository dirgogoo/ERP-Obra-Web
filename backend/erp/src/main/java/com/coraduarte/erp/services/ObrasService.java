package com.coraduarte.erp.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.coraduarte.erp.models.Obras;
import com.coraduarte.erp.repositories.ObraRepository;

public class ObrasService {
    
  @Autowired
  private ObraRepository obraRepository;

  public Obras findById(Long id){
    Optional<Obras> obra = this.obraRepository.findById(id);
    return obra.orElseThrow(() -> new RuntimeException(
     "Obra não encontrada! Id: " + id + ", Tipo: " + Obras.class.getName()
    ));
  }

  public Obras create(Obras obj){
     obj.setId(null);
     obj = this.obraRepository.save(obj);
  }

}
