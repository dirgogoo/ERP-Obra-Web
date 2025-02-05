package com.coraduarte.erp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.coraduarte.erp.models.Etapas;
import com.coraduarte.erp.repositories.EtapasRepository;

public class EtapaService {
    
    @Autowired
    private EtapasRepository etapasRepository;

    public Etapas findById(Long id){
       Optional<Etapas> etapa = this.etapasRepository.findById(id);
      return etapa.orElseThrow(( )-> new RuntimeException(
            "Etapa não encontrada! Id: " + id + ", Tipo:" + Etapas.class.getName()
        ));
    }

    @Transactional
    private Etapas create(Etapas obj){
        obj.setId(null);
        obj = this.etapasRepository.save(obj);
        return obj;
    }

    @Transactional
    private Etapas update(Etapas obj){
      Etapas newObj = this.findById(obj.getId());
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
           throw new RuntimeException("Não é possível excluir!");
        }
    }

}
