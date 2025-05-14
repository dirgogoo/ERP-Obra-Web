package com.coraduarte.erp.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

import com.coraduarte.erp.models.Etapa;
import com.coraduarte.erp.models.Regiao;
import com.coraduarte.erp.models.enums.ProfileEnum;
import com.coraduarte.erp.repositories.RegiaoRepository;
import com.coraduarte.erp.security.UserSpringSecurity;
import com.coraduarte.erp.services.exceptions.ObjectNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class RegiaoService {
    
    @Autowired
    RegiaoRepository regiaoRepository;

    public Regiao findById (Long id){
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        Optional<Regiao> regiao = this.regiaoRepository.findById(id);
        return regiao.orElseThrow(() -> new ObjectNotFoundException(
                "Item não encontrado!"));
    }

     @Transactional
    public Regiao create(Regiao obj) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        obj.setId(null);
        obj = this.regiaoRepository.save(obj); 
        return obj;
    }

    public Page<Regiao> findAll(String nome,Pageable pageable){
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        if (pageable == null || pageable.isUnpaged()) {
            pageable = PageRequest.of(0, 10, Sort.by("id").ascending());
        }

        Page<Regiao> regiaos = this.regiaoRepository.findByNomeContainingIgnoreCase(nome,pageable);
        return regiaos;
    }

    public List<Regiao> findAll() {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        List<Regiao> regioes = this.regiaoRepository.findAll();

        return regioes;
    }


    public void delete(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.ADMIN))) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        findById(id);
        try {
            this.regiaoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir este fornecedor!");
        }
    }
}

