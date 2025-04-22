package com.coraduarte.erp.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.coraduarte.erp.models.Regiao;
import com.coraduarte.erp.services.RegiaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/regiao")
@Validated
public class RegiaoController {
    
    @Autowired
    RegiaoService regiaoService;

     @GetMapping("/{id}")
    public ResponseEntity<Regiao> findById(@PathVariable Long id){
       Regiao obj = this.regiaoService.findById(id);
       return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> create (@Valid @RequestBody Regiao obj){
        this.regiaoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
