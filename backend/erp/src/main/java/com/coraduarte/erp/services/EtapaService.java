package com.coraduarte.erp.services;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.transaction.annotation.Transactional;

import com.coraduarte.erp.models.Etapa;
import com.coraduarte.erp.models.enums.ProfileEnum;
import com.coraduarte.erp.repositories.EtapaRepository;
import com.coraduarte.erp.security.UserSpringSecurity;

public class EtapaService {

    @Autowired
    private EtapaRepository etapasRepository;

    public Etapa findById(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        Optional<Etapa> etapa = this.etapasRepository.findById(id);
        return etapa.orElseThrow(() -> new RuntimeException(
                "Etapa não encontrada! Id: " + id + ", Tipo:" + Etapa.class.getName()
        ));
    }

    @Transactional
    private Etapa create(Etapa obj) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        obj.setId(null);
        obj = this.etapasRepository.save(obj);
        return obj;
    }

    @Transactional
    private Etapa update(Etapa obj) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        Etapa newObj = this.findById(obj.getId());
        newObj.setName(obj.getName());
        newObj.setPrice(obj.getPrice());
        newObj.setDeadline(obj.getDeadline());

        return this.etapasRepository.save(newObj);

    }

    public void delete(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.ADMIN))) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        findById(id);
        try {
            this.etapasRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir! ");
        }
    }

}
