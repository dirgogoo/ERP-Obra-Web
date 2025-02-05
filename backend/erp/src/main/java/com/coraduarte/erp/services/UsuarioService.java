package com.coraduarte.erp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import com.coraduarte.erp.models.Usuario;
import com.coraduarte.erp.repositories.UsuarioRepository;

public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findById(Long id){
    Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        return usuario.orElseThrow(( )-> new RuntimeException(
            "Usuário não encontrado! Id: " + id + ", Tipo:" + Usuario.class.getName()
        ));
   } 

   @Transactional
   public Usuario create(Usuario obj){
    obj.setId(null);
    obj = this.usuarioRepository.save(obj);
    return obj;
   }

   @Transactional
   public Usuario update(Usuario obj){
     Usuario newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.usuarioRepository.save(newObj);
   }

   public void delete(Long id){
    findById(id);
      try {
          this.usuarioRepository.deleteById(id);
      } catch (Exception e) {
         throw new RuntimeException("Não é possível excluir este usuário!");
      }
   }
}
