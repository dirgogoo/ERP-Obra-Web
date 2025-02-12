package com.coraduarte.erp.services;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.coraduarte.erp.models.Servico;
import com.coraduarte.erp.models.enums.ProfileEnum;
import com.coraduarte.erp.repositories.ServicoRepository;
import com.coraduarte.erp.security.UserSpringSecurity;

@Service
public class ServicoService {  

    @Autowired
   private ServicoRepository servicoRepository;

    public Servico findById(Long id){
     Optional<Servico> cliente = this.servicoRepository.findById(id);
        return cliente.orElseThrow(( )-> new RuntimeException(
            "Serviço não encontrado! Id: " + id + ", Tipo:" + Servico.class.getName()));
    }

    @Transactional
    public Servico create(Servico obj) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        obj.setId(null);
        obj = this.servicoRepository.save(obj);
        return obj;
    }

    public void delete(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.ADMIN))) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        findById(id);
        try {
            this.servicoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir!");
        }
    }
}




   
