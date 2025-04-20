package com.coraduarte.erp.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coraduarte.erp.models.EtapaObra;
import com.coraduarte.erp.models.Item;
import com.coraduarte.erp.models.ItemEtapa;
import com.coraduarte.erp.models.enums.ProfileEnum;
import com.coraduarte.erp.repositories.ItemEtapaRepository;
import com.coraduarte.erp.security.UserSpringSecurity;
import com.coraduarte.erp.services.exceptions.ObjectNotFoundException;

@Service
public class ItemEtapaService {


    @Autowired
    private EtapaObraService etapaObraService;
    
    @Autowired
    private ItemEtapaRepository itemEtapaRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UtilService utilService;
    
    
        public ItemEtapa findById(Long id) {
            UserSpringSecurity userSpringSecurity = UserService.authenticated();
    
            if (Objects.isNull(userSpringSecurity)) {
                throw new AuthorizationDeniedException("Acesso negado!");
            }
    
            Optional<ItemEtapa> itemEtapa = this.itemEtapaRepository.findById(id);
        return itemEtapa.orElseThrow(() -> new ObjectNotFoundException(
                "Item da etapa não encontrado!"
        ));
    }


    public List<ItemEtapa> findAllbyEtapaObraIdAll(Long id){
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        List<ItemEtapa> items = this.itemEtapaRepository.findAllByEtapaObra_Id(id);
        return items;
    }

    @Transactional
    public ItemEtapa create(ItemEtapa obj) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.ADMIN))) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }
        obj.setId(null);

        Item item = this.itemService.findById(obj.getItem().getId());
        obj.setItem(item);

        EtapaObra etapa = this.etapaObraService.findById(obj.getEtapa().getId());
        obj.setEtapa(etapa);

        obj.setDataLancamento(this.utilService.getTodayDate());
        
        obj = this.itemEtapaRepository.save(obj);
        return obj;

    }

    @Transactional
    public ItemEtapa update(ItemEtapa obj) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.ADMIN))) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        ItemEtapa itemUpdated =  findById(obj.getId());
        Item item = this.itemService.findById(obj.getItem().getId());

        itemUpdated.setQuantidade(obj.getQuantidade());
        itemUpdated.setItem(item);
    

        obj = this.itemEtapaRepository.save(itemUpdated);
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
