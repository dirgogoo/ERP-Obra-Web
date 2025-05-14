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

import com.coraduarte.erp.models.Fornecedor;
import com.coraduarte.erp.models.Regiao;
import com.coraduarte.erp.models.enums.ProfileEnum;
import com.coraduarte.erp.repositories.FornecedorRepository;
import com.coraduarte.erp.services.exceptions.ObjectNotFoundException;
import com.coraduarte.erp.security.UserSpringSecurity;

import jakarta.transaction.Transactional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private RegiaoService regiaoService;

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

    public Page<Fornecedor> findAll(String nome, Pageable pageable) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        if (pageable == null || pageable.isUnpaged()) {
            pageable = PageRequest.of(0, 10, Sort.by("id").ascending());
        }

        Page<Fornecedor> fornecedores = this.fornecedorRepository.findByNomeContainingIgnoreCase(nome, pageable);
        return fornecedores;
    }

    public List<Fornecedor> findAll() {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        List<Fornecedor> fornecedores = this.fornecedorRepository.findAll();

        return fornecedores;
    }


    @Transactional
    public Fornecedor create(Fornecedor obj) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        Regiao regionFounded = this.regiaoService.findById(obj.getRegiao().getId());
        obj.setId(null);
        obj.setRegiao(regionFounded); // Initialize regiao to avoid null pointer exception

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
