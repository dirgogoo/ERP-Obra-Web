package com.coraduarte.erp.services;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDeniedException;

import com.coraduarte.erp.models.Item;
import com.coraduarte.erp.models.enums.ProfileEnum;
import com.coraduarte.erp.repositories.ItemRepository;
import com.coraduarte.erp.security.UserSpringSecurity;

public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item findById(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        Optional<Item> item = this.itemRepository.findById(id);
        return item.orElseThrow(() -> new RuntimeException(
                "Item não encontrado!"
        ));
    }

    public Item create(Item obj) {

        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        obj.setId(null);
        obj = this.itemRepository.save(obj);
        return obj;
    }

    public void delete(Long id) {

        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.ADMIN))) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        findById(id);
        try {
            this.itemRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível excluir este item!");
        }
    }

}
