package com.coraduarte.erp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.coraduarte.erp.models.Item;
import com.coraduarte.erp.repositories.ItemRepository;

public class ItemService {
    
   @Autowired
   private ItemRepository itemRepository;

   public Item findById(Long id){
    Optional<Item> item = this.itemRepository.findById(id);
    return item.orElseThrow(()-> new RuntimeException(
        "Item não encontrado!"
    ));
   }

   public Item create(Item obj){
    obj.setId(null);
    obj = this.itemRepository.save(obj);
    return obj;
   }

   public void delete(Long id){
    findById(id);
    try {
        this.itemRepository.deleteById(id);
    } catch (Exception e) {
        throw new RuntimeException("Não foi possível excluir este item!");
    }
   }

}
