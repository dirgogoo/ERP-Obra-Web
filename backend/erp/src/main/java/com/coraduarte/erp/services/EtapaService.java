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
import org.springframework.transaction.annotation.Transactional;

import com.coraduarte.erp.models.Etapa;
import com.coraduarte.erp.models.enums.ProfileEnum;
import com.coraduarte.erp.repositories.EtapaRepository;
import com.coraduarte.erp.security.UserSpringSecurity;
import com.coraduarte.erp.services.exceptions.ObjectNotFoundException;

@Service
public class EtapaService {

    @Autowired
    private EtapaRepository etapaRepository;

    public Etapa findById(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        Optional<Etapa> etapa = this.etapaRepository.findById(id);
        return etapa.orElseThrow(() -> new ObjectNotFoundException(
                "Etapa não encontrada! Id: " + id + ", Tipo:" + Etapa.class.getName()
        ));
    }

    public Page<Etapa> findAll(Pageable pageable){
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        if (pageable == null || pageable.isUnpaged()) {
            pageable = PageRequest.of(0, 10, Sort.by("id").ascending());
        }

        Page<Etapa> etapas = this.etapaRepository.findAll(pageable);
        return etapas;
    }

    public List<Etapa> findAll() {
        return etapaRepository.findAll();
    }

    @Transactional
    public Etapa create(Etapa obj) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        obj.setId(null);
        obj = this.etapaRepository.save(obj);
        return obj;
    }

    @Transactional
    public Etapa update(Etapa obj) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        Etapa newObj = this.findById(obj.getId());
        newObj.setName(obj.getName());

        return this.etapaRepository.save(newObj);

    }

    public void delete(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.ADMIN))) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        findById(id);
        try {
            this.etapaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir! ");
        }
    }

}
