package com.coraduarte.erp.services;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.coraduarte.erp.models.User;
import com.coraduarte.erp.models.enums.ProfileEnum;
import com.coraduarte.erp.repositories.UserRepository;
import com.coraduarte.erp.security.UserSpringSecurity;
import com.coraduarte.erp.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findById(Long id) {
        UserSpringSecurity authenticated = UserService.authenticated();

        if (Objects.nonNull(authenticated) && authenticated.hasRole(ProfileEnum.SUPERADMIN) || authenticated.getId().equals(id)) {
            Optional<User> usuario = this.usuarioRepository.findById(id);
            return usuario.orElseThrow(() -> new ObjectNotFoundException(
                    "Usuário não encontrado! Id: " + id + ", Tipo:" + User.class.getName()
            ));
        }
        return null;
    }

       public Page<User> findAll(Pageable pageable){
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        if (pageable == null || pageable.isUnpaged()) {
            pageable = PageRequest.of(0, 10, Sort.by("id").ascending());
        }

        Page<User> users = this.usuarioRepository.findAll(pageable);
        return users;
    }


    @Transactional
    public User create(User obj) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.SUPERADMIN))) {
          // throw new AuthorizationDeniedException("Acesso negado!");
        }

        if (usuarioRepository.existsByUsername(obj.getUsername())) {
    throw new ResponseStatusException(HttpStatus.CONFLICT, "Nome de usuário já está em uso.");
}


        obj.setId(null);
        obj.setPassword(this.bCryptPasswordEncoder.encode(obj.getPassword()));
        obj = this.usuarioRepository.save(obj);
        return obj;
    }
    

    @Transactional
    public User update(User obj) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.SUPERADMIN))) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.usuarioRepository.save(newObj);
    }

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
}
