package com.coraduarte.erp.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coraduarte.erp.models.EtapaObra;
import com.coraduarte.erp.models.enums.ProfileEnum;
import com.coraduarte.erp.models.itemEtapa;
import com.coraduarte.erp.repositories.EtapaObraRepository;
import com.coraduarte.erp.security.UserSpringSecurity;
import com.coraduarte.erp.services.exceptions.ObjectNotFoundException;

@Service
public class EtapaObraService {

    @Autowired
    private EtapaObraRepository etapaObraRepository;

    @Autowired
    @Lazy
    private ItemEtapaService itemEtapaService;


    public EtapaObra findById(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        Optional<EtapaObra> etapa = this.etapaObraRepository.findById(id);
        return etapa.orElseThrow(() -> new ObjectNotFoundException(
                "Etapa não encontrada!"
        ));
    }

    public Double findSaldoById(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        Optional<EtapaObra> etapa = this.etapaObraRepository.findById(id);
        List<itemEtapa> items = this.itemEtapaService.findAllbyEtapaObraIdAll(id);

        Double saldo = 0.0;
        for (itemEtapa item : items) {
            if (Objects.nonNull(item)) {
                saldo += item.getQuantidade()*item.getItem().getValor();
            }
        }

        saldo = etapa.get().getPrice() - saldo;
        return saldo;
    }

    

    public List<EtapaObra> findAllByObraId(Long id){
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }
        

        List<EtapaObra> etapas = this.etapaObraRepository.findAllByObra_Id(id);
        return etapas;


    }

    

    @Transactional
    public EtapaObra create(EtapaObra obj) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.ADMIN))) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        obj.setId(null);
        obj = this.etapaObraRepository.save(obj);
        return obj;
    }

    public void delete(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.ADMIN))) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        findById(id);
        try {
            this.etapaObraRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir esta etapa da obra!");
        }
    }

}
