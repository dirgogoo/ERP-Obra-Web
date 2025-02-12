package com.coraduarte.erp.services;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authorization.AuthorizationDeniedException;

import com.coraduarte.erp.models.Obras;
import com.coraduarte.erp.models.enums.ProfileEnum;
import com.coraduarte.erp.models.projection.ObraSearchProjection;
import com.coraduarte.erp.repositories.ObraRepository;
import com.coraduarte.erp.security.UserSpringSecurity;

public class ObraService {

    @Autowired
    private ObraRepository obraRepository;

    public Obras findById(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        Optional<Obras> obra = this.obraRepository.findById(id);
        return obra.orElseThrow(() -> new RuntimeException(
                "Obra não encontrada! Id: " + id + ", Tipo: " + Obras.class.getName()
        ));
    }

    public Page<ObraSearchProjection> findAll(Pageable pageable){
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        if (pageable == null || pageable.isUnpaged()) {
            pageable = PageRequest.of(0, 10, Sort.by("id").ascending());
        }

        Page<ObraSearchProjection> obras = this.obraRepository.findAllBy(pageable);
        return obras;
    }

    public Obras create(Obras obj) {
      UserSpringSecurity userSpringSecurity = UserService.authenticated();

      if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.ADMIN)) )
          throw new AuthorizationDeniedException("Acesso negado!");

        obj.setId(null);
        obj = this.obraRepository.save(obj);
        return obj;
    }

    public Obras update(Obras obj) {

        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.ADMIN))) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        Obras newObj = this.findById(obj.getId());
        newObj.setNome(obj.getNome());
        newObj.setDataInicio(obj.getDataInicio());
        newObj.setDataPrevista(obj.getDataPrevista());
        return obj;
    }

    public void delete(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.ADMIN))) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }
        findById(id);
        try {
            this.obraRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir esta obra!");
        }
    }

}
