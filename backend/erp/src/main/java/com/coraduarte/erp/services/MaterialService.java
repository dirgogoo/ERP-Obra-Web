package com.coraduarte.erp.services;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.transaction.annotation.Transactional;

import com.coraduarte.erp.models.Materiais;
import com.coraduarte.erp.models.enums.ProfileEnum;
import com.coraduarte.erp.repositories.MaterialRepository;
import com.coraduarte.erp.security.UserSpringSecurity;

public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public Materiais findById(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        Optional<Materiais> material = this.materialRepository.findById(id);
        return material.orElseThrow(() -> new RuntimeException(
                "Material não encontrado! Id: " + id + ", Tipo:" + Materiais.class.getName()
        ));
    }

    @Transactional
    public Materiais create(Materiais obj) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        obj.setId(null);
        obj = this.materialRepository.save(obj);
        return obj;
    }

    public void delete(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.ADMIN))) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        findById(id);
        try {
            this.materialRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir!");
        }
    }

}
