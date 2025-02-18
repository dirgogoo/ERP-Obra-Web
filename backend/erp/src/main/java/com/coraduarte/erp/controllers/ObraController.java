package com.coraduarte.erp.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.coraduarte.erp.models.Obra;
import com.coraduarte.erp.services.ObraService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/obra")
@Validated
public class ObraController {
   
    @Autowired
    private ObraService obraService;

    @GetMapping("/{id}")
    public ResponseEntity<Obra> findById(@PathVariable Long id){
       Obra obj = this.obraService.findById(id);
       return ResponseEntity.ok().body(obj);

    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody Obra obj){
       this.obraService.create(obj);
       URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
       return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.obraService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
