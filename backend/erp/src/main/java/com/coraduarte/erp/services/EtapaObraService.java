package com.coraduarte.erp.services;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.transaction.annotation.Transactional;

import com.coraduarte.erp.models.Etapa;
import com.coraduarte.erp.models.enums.ProfileEnum;
import com.coraduarte.erp.repositories.EtapaObraRepository;
import com.coraduarte.erp.security.UserSpringSecurity;

public class EtapaObraService {

    @Autowired
    private EtapaObraRepository etapaObraRepository;

    public Etapa findById(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        Optional<Etapa> etapa = this.etapaObraRepository.findById(id);
        return etapa.orElseThrow(() -> new RuntimeException(
                "Etapa não encontrada!"
        ));
    }

    @Transactional
    public Etapa create(Etapa obj) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.ADMIN))) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        obj.setId(null);
        obj = this.etapaObraRepository.save(obj);
        return obj;
    }

    public void delete(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.ADMIN))) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        findById(id);
        try {
            this.etapaObraRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir esta etapa da obra!");
        }
    }

}
