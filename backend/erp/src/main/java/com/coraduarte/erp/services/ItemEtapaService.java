package com.coraduarte.erp.services;

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

import com.coraduarte.erp.models.EtapaObra;
import com.coraduarte.erp.models.enums.ProfileEnum;
import com.coraduarte.erp.models.itemEtapa;
import com.coraduarte.erp.repositories.ItemEtapaRepository;
import com.coraduarte.erp.security.UserSpringSecurity;

@Service
public class ItemEtapaService {


    @Autowired
    private EtapaObraService etapaObraService;
    
    private ItemEtapaRepository itemEtapaRepository;
    
    
        public itemEtapa findById(Long id) {
            UserSpringSecurity userSpringSecurity = UserService.authenticated();
    
            if (Objects.isNull(userSpringSecurity)) {
                throw new AuthorizationDeniedException("Acesso negado!");
            }
    
            Optional<itemEtapa> itemEtapa = this.itemEtapaRepository.findById(id);
        return itemEtapa.orElseThrow(() -> new RuntimeException(
                "Item da etapa não encontrado!"
        ));
    }

    public Page<itemEtapa> findAllbyEtapaObraId(Long id ,Pageable pageable){
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        if (pageable == null || pageable.isUnpaged()) {
            pageable = PageRequest.of(0, 10, Sort.by("id").ascending());
        }

        Page<itemEtapa> items = this.itemEtapaRepository.findAllByEtapaObra_Id(id, pageable);
        return items;
    }

    @Transactional
    public itemEtapa create(itemEtapa obj) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.ADMIN))) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        EtapaObra etapa = this.etapaObraService.findById(obj.getEtapa().getId());
        obj.setEtapa(etapa);

        obj.setId(null);
        obj = this.itemEtapaRepository.save(obj);
        return obj;

    }

    

    public void delete(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.ADMIN))) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        findById(id);

        try {
            this.itemEtapaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível excluir este item!");
        }
    }

}
