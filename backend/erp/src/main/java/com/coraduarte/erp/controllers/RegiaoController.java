package com.coraduarte.erp.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.coraduarte.erp.models.Etapa;
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

    @GetMapping("/all")
    public ResponseEntity<List<Regiao>> findAll(){
        List<Regiao> regioes = this.regiaoService.findAll();
        return ResponseEntity.ok().body(regioes);
    }

    @GetMapping
    public ResponseEntity<Page<Regiao>> findAll(@RequestParam(value="search",required = false, defaultValue = "") String nome,Pageable pageable){
       Page<Regiao> regioes = this.regiaoService.findAll(nome,pageable);
       return ResponseEntity.ok().body(regioes);
    }

    @PostMapping
    public ResponseEntity<Void> create (@Valid @RequestBody Regiao obj){
        this.regiaoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
