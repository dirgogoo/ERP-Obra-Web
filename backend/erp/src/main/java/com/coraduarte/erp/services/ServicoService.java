package com.coraduarte.erp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.coraduarte.erp.models.Servico;
import com.coraduarte.erp.repositories.ServicoRepository;

public class ServicoService {
    
   @Autowired
   private ServicoRepository servicoRepository;

   public Servico findById(Long id){
    Optional<Servico> cliente = this.servicoRepository.findById(id);
        return cliente.orElseThrow(( )-> new RuntimeException(
            "Serviço não encontrado! Id: " + id + ", Tipo:" + Servico.class.getName()
        ));
   } 

   @Transactional
   public Servico create(Servico obj){
    obj.setId(null);
    obj = this.servicoRepository.save(obj);
    return obj;
   }

   
   public void delete(Long id){
    findById(id);
      try {
          this.servicoRepository.deleteById(id);
      } catch (Exception e) {
         throw new RuntimeException("Não é possível excluir!" );
      }
   }
}
