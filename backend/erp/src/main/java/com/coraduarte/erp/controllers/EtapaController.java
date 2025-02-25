package com.coraduarte.erp.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.coraduarte.erp.models.Etapa;
import com.coraduarte.erp.services.EtapaService;

import jakarta.validation.Valid;

@RestController
public class EtapaController {
    
   @Autowired 
   private EtapaService etapaService;

   @GetMapping("/{id}")
   public ResponseEntity<Etapa> findById(@PathVariable Long id){
     Etapa obj = this.etapaService.findById(id);
     return ResponseEntity.ok().body(obj);
   }

   @PostMapping
   public ResponseEntity<Void> create (@Valid @RequestBody Etapa obj){
     this.etapaService.create(obj);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
      return ResponseEntity.created(uri).build();
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id){
     this.etapaService.delete(id);
     return ResponseEntity.noContent().build();
   }

}
