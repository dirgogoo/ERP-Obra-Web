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

import com.coraduarte.erp.models.Material;
import com.coraduarte.erp.models.User.CreateUser;
import com.coraduarte.erp.services.MaterialService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/materiais")
@Validated
public class MaterialController {
    
   @Autowired
   private MaterialService materialService;

   @GetMapping("/{id}")
   public ResponseEntity<Material> findById(@PathVariable Long id){
    Material obj = this.materialService.findById(id);
    return ResponseEntity.ok().body(obj);
   }

   @PostMapping
   @Validated(CreateUser.class)
   public ResponseEntity<Void> create(@Valid @RequestBody Material obj){
     this.materialService.create(obj);
     URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
     return ResponseEntity.created(uri).build();
   }

   @DeleteMapping("/{id}")
   public ResponseEntity <Void> delete(@PathVariable Long id){
     this.materialService.delete(id);
     return ResponseEntity.noContent().build(); 
   }

}
