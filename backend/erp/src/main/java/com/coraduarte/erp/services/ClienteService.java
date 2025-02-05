package com.coraduarte.erp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coraduarte.erp.models.Cliente;
import com.coraduarte.erp.repositories.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class ClienteService {
   
    @Autowired
    private ClienteRepository clienteRepository;


    public Cliente findById(Long id){
        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        return cliente.orElseThrow(( )-> new RuntimeException(
            "Usuráio não encontrado! Id: " + id + ", Tipo:" + Cliente.class.getName()
        ));
    }

    @Transactional
    public Cliente create(Cliente obj) {
      obj.setId(null);
      obj = this.clienteRepository.save(obj);
      return obj;
    }

    /*@Transactional
    public Cliente update(Cliente obj){
        Cliente newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.clienteRepository.save(newObj);
    } */

    public void delete(Long id){
        findById(id);
        try {
            this.clienteRepository.deleteById(id);
        } catch (Exception e) {
           throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!" );
        }
    }


}
