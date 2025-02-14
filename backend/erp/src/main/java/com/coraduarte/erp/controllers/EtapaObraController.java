package com.coraduarte.erp.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.coraduarte.erp.models.EtapaObra;
import com.coraduarte.erp.services.EtapaObraService;

import jakarta.validation.Valid;

@RestController
public class EtapaObraController {
    
    @Autowired
    private EtapaObraService etapaObraService;

    public ResponseEntity<EtapaObra> findById(@PathVariable Long id){
        EtapaObra obj = this.etapaObraService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    public ResponseEntity<Void> create(@Valid @RequestBody EtapaObra obj){
        this.etapaObraService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.etapaObraService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
