package com.coraduarte.erp.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.coraduarte.erp.models.itemEtapa;
import com.coraduarte.erp.services.ItemEtapaService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/obra/etapa/item")
public class ItemEtapaController {
    
    @Autowired
    private ItemEtapaService itemEtapaService;

    @GetMapping("/{id}")
    public ResponseEntity <itemEtapa> findById(@PathVariable Long id){
       itemEtapa obj = this.itemEtapaService.findById(id);
       return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/EtapaObra/{id}")
    public ResponseEntity <Page<itemEtapa>> findByEtapaObraId(@PathVariable Long id, Pageable pageable){
        Page<itemEtapa> obj = this.itemEtapaService.findAllbyEtapaObraId(id, pageable);
       return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> create (@Valid @RequestBody itemEtapa obj){
        this.itemEtapaService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.itemEtapaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
