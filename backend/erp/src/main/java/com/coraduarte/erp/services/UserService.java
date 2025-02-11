package com.coraduarte.erp.services;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coraduarte.erp.models.User;
import com.coraduarte.erp.models.enums.ProfileEnum;
import com.coraduarte.erp.repositories.UserRepository;
import com.coraduarte.erp.security.UserSpringSecurity;

@Service
public class UserService {

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findById(Long id) {
      
         UserSpringSecurity authenticated = UserService.authenticated();

        if (Objects.isNull(authenticated) && ( !( authenticated.hasRole(ProfileEnum.SUPERADMIN)) || ! (authenticated.getId().equals(id))) ) 
            return null;
        
        
        Optional<User> usuario = this.usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new RuntimeException(
                "Usuário não encontrado! Id: " + id + ", Tipo:" + User.class.getName()
        ));
    }

    @Transactional
    public User create(User obj) {
        obj.setId(null);
        obj.setPassword(this.bCryptPasswordEncoder.encode(obj.getPassword()));
        obj.setProfiles(Stream.of(ProfileEnum.USER.getCode()).collect(Collectors.toSet()));
        obj = this.usuarioRepository.save(obj);
        return obj;
    }

<<<<<<< HEAD
    /*  @Transactional
   public Usuario update(Usuario obj){
     Usuario newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.usuarioRepository.save(newObj);
   }
     */
    public void delete(Long id) {
        findById(id);
        try {
            this.usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir este usuário!");
        }
    }

    public static UserSpringSecurity authenticated() {
        try {
            return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

=======
   @Transactional
   public User update(User obj){
     User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.usuarioRepository.save(newObj);
   }

   public void delete(Long id){
    findById(id);
      try {
          this.usuarioRepository.deleteById(id);
      } catch (Exception e) {
         throw new RuntimeException("Não é possível excluir este usuário!" );
      }
   }
>>>>>>> b12e60fb04faef935b5bb5b9295f2c0ad3835b95
}
