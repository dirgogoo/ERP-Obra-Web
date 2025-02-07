package com.coraduarte.erp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.coraduarte.erp.models.Etapa;
import com.coraduarte.erp.repositories.EtapaRepository;

public class EtapaService {
    
    @Autowired
    private EtapaRepository etapasRepository;

    public Etapa findById(Long id){
       Optional<Etapa> etapa = this.etapasRepository.findById(id);
      return etapa.orElseThrow(( )-> new RuntimeException(
            "Etapa não encontrada! Id: " + id + ", Tipo:" + Etapa.class.getName()
        ));
    }

    @Transactional
    private Etapa create(Etapa obj){
        obj.setId(null);
        obj = this.etapasRepository.save(obj);
        return obj;
    }

    @Transactional
    private Etapa update(Etapa obj){
      Etapa newObj = this.findById(obj.getId());
      newObj.setName(obj.getName());
      newObj.setPrice(obj.getPrice());
      newObj.setDeadline(obj.getDeadline());

      return this.etapasRepository.save(newObj);
        
    }

    public void delete(Long id){
        findById(id);
        try {
            this.etapasRepository.deleteById(id);
        } catch (Exception e) {
           throw new RuntimeException("Não é possível excluir! ");
        }
    }

}
