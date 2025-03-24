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

import com.coraduarte.erp.models.Item;
import com.coraduarte.erp.models.enums.ProfileEnum;
import com.coraduarte.erp.repositories.ItemRepository;
import com.coraduarte.erp.security.UserSpringSecurity;
import com.coraduarte.erp.services.exceptions.ObjectNotFoundException;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item findById(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        Optional<Item> item = this.itemRepository.findById(id);
        return item.orElseThrow(() -> new ObjectNotFoundException(
                "Item não encontrado!"
        ));
    }

    public Page<Item> findAllByTipo(Pageable pageable, Integer Tipo){
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        if (pageable == null || pageable.isUnpaged()) {
            pageable = PageRequest.of(0, 10, Sort.by("id").ascending());
        }

        Page<Item> items = this.itemRepository.findAllByTipo(Tipo, pageable);
        return items;
    }

    public Page<Item> findAll(Pageable pageable){
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        if (pageable == null || pageable.isUnpaged()) {
            pageable = PageRequest.of(0, 10, Sort.by("id").ascending());
        }

        Page<Item> items = this.itemRepository.findAll(pageable);
        return items;
    }

    public Item create(Item obj) {

        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }
        System.out.println(obj.getTipo());
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
