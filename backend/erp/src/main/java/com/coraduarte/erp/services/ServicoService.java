package com.coraduarte.erp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.coraduarte.erp.models.Servicos;
import com.coraduarte.erp.repositories.ServicosRepository;

public class ServicoService {
    
   @Autowired
   private ServicosRepository servicosRepository;

   public Servicos findById(Long id){
    Optional<Servicos> cliente = this.servicosRepository.findById(id);
        return cliente.orElseThrow(( )-> new RuntimeException(
            "Serviço não encontrado! Id: " + id + ", Tipo:" + Servicos.class.getName()
        ));
   } 

   @Transactional
   public Servicos create(Servicos obj){
    obj.setId(null);
    obj = this.servicosRepository.save(obj);
    return obj;
   }

   public void delete(Long id){
    findById(id);
      try {
          this.servicosRepository.deleteById(id);
      } catch (Exception e) {
         throw new RuntimeException("Não é possível excluir!" );
      }
   }
}
