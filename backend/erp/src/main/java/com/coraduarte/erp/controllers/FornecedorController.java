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

import com.coraduarte.erp.services.FornecedorService;

import jakarta.validation.Valid;

import com.coraduarte.erp.models.Fornecedor;
import com.coraduarte.erp.models.Regiao;


@RestController
@RequestMapping("/fornecedor")
@Validated
public class FornecedorController {
    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> findById(@PathVariable Long id){
       Fornecedor obj = this.fornecedorService.findById(id);
       return ResponseEntity.ok().body(obj);
    }

        @GetMapping("/all")
        public ResponseEntity<List<Fornecedor>> findAll(){
            List<Fornecedor> fornecedores = this.fornecedorService.findAll();
            return ResponseEntity.ok().body(fornecedores);
        }

        @GetMapping
        public ResponseEntity<Page<Fornecedor>> findAll(@RequestParam(value="search",required = false, defaultValue = "") String nome, Pageable pageable){
           Page<Fornecedor> fornecedores = this.fornecedorService.findAll(nome, pageable);
           return ResponseEntity.ok().body(fornecedores);
        }


    @PostMapping
    public ResponseEntity<Void> create (@Valid @RequestBody Fornecedor obj){
        this.fornecedorService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    
}
