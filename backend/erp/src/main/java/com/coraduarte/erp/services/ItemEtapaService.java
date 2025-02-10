package com.coraduarte.erp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.coraduarte.erp.models.ItemEtapa;
import com.coraduarte.erp.repositories.ItemEtapaRepository;

public class ItemEtapaService {
    
    @Autowired
    private ItemEtapaRepository itemEtapaRepository;

    public ItemEtapa findById(Long id){
        Optional<ItemEtapa> itemEtapa = this.itemEtapaRepository.findById(id);
        return itemEtapa.orElseThrow(()-> new RuntimeException(
            "Item da etapa não encontrado!"
        ));
    }

    @Transactional
    public ItemEtapa create(ItemEtapa obj){
        obj.setId(null);
        obj = this.itemEtapaRepository.save(obj);
        return obj;
    }

    public void delete(Long id){
        findById(id);

        try {
            this.itemEtapaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível excluir este item!");
        }
    }

}
