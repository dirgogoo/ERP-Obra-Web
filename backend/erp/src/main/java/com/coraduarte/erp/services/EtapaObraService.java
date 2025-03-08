package com.coraduarte.erp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coraduarte.erp.models.Etapa;
import com.coraduarte.erp.models.EtapaObra;
import com.coraduarte.erp.models.ItemEtapa;
import com.coraduarte.erp.models.Obra;
import com.coraduarte.erp.models.enums.ProfileEnum;
import com.coraduarte.erp.repositories.EtapaObraRepository;
import com.coraduarte.erp.security.UserSpringSecurity;

@Service
public class EtapaObraService {

    @Autowired
    private EtapaObraRepository etapaObraRepository;

    @Autowired
    private ItemEtapaService itemEtapaService;

    @Autowired
    private EtapaService etapaService;
   
    @Autowired
    private ObraService obraService;

    


    public EtapaObra findById(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        Optional<EtapaObra> etapa = this.etapaObraRepository.findById(id);
        return etapa.orElseThrow(() -> new RuntimeException(
                "Etapa não encontrada!"
        ));
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

        List<ItemEtapa> itemEtapas = new ArrayList<>();

        try{
        Obra obra = this.obraService.findById(obj.getObra().getId());
        obj.setObra(obra);

        Etapa etapa = this.etapaService.findById(obj.getEtapa().getId());
        obj.setEtapa(etapa);


        for(ItemEtapa item : obj.getItens()){
           itemEtapas.add(this.itemEtapaService.findById(item.getId()));
        }
    }
        catch(Exception e){
           throw new RuntimeException("Falha ao criar etapa obra");
        }
    
        obj.setItens(itemEtapas);
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
