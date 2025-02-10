package com.coraduarte.erp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.coraduarte.erp.models.Etapa;
import com.coraduarte.erp.repositories.EtapaObraRepository;

public class EtapaObraService {
    
    @Autowired
    private EtapaObraRepository etapaObraRepository;

    public Etapa findById(Long id){
        Optional<Etapa> etapa = this.etapaObraRepository.findById(id);
        return etapa.orElseThrow(() -> new RuntimeException(
            "Etapa não encontrada!"
        ));
    }

    @Transactional
    public Etapa create(Etapa obj){
        obj.setId(null);
        obj = this.etapaObraRepository.save(obj);
        return obj;
    }

    public void delete(Long id){
        findById(id);
        try {
            this.etapaObraRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir esta etapa da obra!" );
        }
    }

}
