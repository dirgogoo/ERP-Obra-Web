package com.coraduarte.erp.services;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

import com.coraduarte.erp.models.Fornecedor;
import com.coraduarte.erp.models.enums.ProfileEnum;
import com.coraduarte.erp.repositories.FornecedorRepository;
import com.coraduarte.erp.services.exceptions.ObjectNotFoundException;
import com.coraduarte.erp.security.UserSpringSecurity;

import jakarta.transaction.Transactional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Fornecedor findById(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        Optional<Fornecedor> fornecedor = this.fornecedorRepository.findById(id);
        return fornecedor.orElseThrow(() -> new ObjectNotFoundException(
                "Fornecedor não encontrado! Id: " + id + ", Tipo: " + Fornecedor.class.getName()
        ));
    }

    @Transactional
    public Fornecedor create(Fornecedor obj) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        obj.setId(null);
        obj = this.fornecedorRepository.save(obj); 
        return obj;
    }

    public void delete(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.ADMIN))) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        findById(id);
        try {
            this.fornecedorRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir este fornecedor!");
        }
    }
}
