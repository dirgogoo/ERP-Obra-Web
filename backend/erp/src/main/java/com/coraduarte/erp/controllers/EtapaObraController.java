package com.coraduarte.erp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coraduarte.erp.models.EtapaObra;
import com.coraduarte.erp.services.EtapaObraService;

@RestController
@RequestMapping("/obra/etapa")
public class EtapaObraController {
    
    @Autowired
    private EtapaObraService etapaObraService;

    @GetMapping("/{id}")
    public ResponseEntity<EtapaObra> findById(@PathVariable Long id){
        EtapaObra obj = this.etapaObraService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.etapaObraService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
