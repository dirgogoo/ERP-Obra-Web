package com.coraduarte.erp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.coraduarte.erp.models.Materiais;
import com.coraduarte.erp.repositories.MateriaisRepository;

public class MaterialService {
    
   @Autowired
   private MateriaisRepository materialRepository;

   public Materiais findById(Long id){
      Optional<Materiais> material = this.materialRepository.findById(id);
      return material.orElseThrow(()-> new RuntimeException(
         "Material não encontrado! Id: " + id + ", Tipo:" + Materiais.class.getName()
      ));
   }

   @Transactional
   public Materiais create(Materiais obj){
      obj.setId(null);
      obj = this.materialRepository.save(obj);
      return obj;
   }

   public void delete(Long id){
      findById(id);
      try {
          this.materialRepository.deleteById(id);
      } catch (Exception e) {
         throw new RuntimeException("Não é possível excluir!");
      }
   }

}
