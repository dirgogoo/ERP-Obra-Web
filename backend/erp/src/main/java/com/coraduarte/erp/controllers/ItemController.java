package com.coraduarte.erp.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.coraduarte.erp.models.Item;
import com.coraduarte.erp.services.ItemService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public class ItemController {
    
    @Autowired
    private ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity <Item> findById(@PathVariable Long id){
        Item obj = this.itemService.findById(id);
        return ResponseEntity.ok().body(obj); 
    }

    @PostMapping
    public ResponseEntity <Void> create(@Valid @RequestBody Item obj){
        this.itemService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete(@PathVariable Long id){
        this.itemService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
