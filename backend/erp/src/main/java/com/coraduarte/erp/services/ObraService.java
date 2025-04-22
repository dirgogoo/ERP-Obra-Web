package com.coraduarte.erp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

import com.coraduarte.erp.models.Cliente;
import com.coraduarte.erp.models.EtapaObra;
import com.coraduarte.erp.models.Obra;
import com.coraduarte.erp.models.enums.ProfileEnum;
import com.coraduarte.erp.models.projection.ObraSearchProjection;
import com.coraduarte.erp.repositories.ObraRepository;
import com.coraduarte.erp.security.UserSpringSecurity;
import com.coraduarte.erp.services.exceptions.ObjectNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ObraService {

    @Autowired
    private ObraRepository obraRepository;

    @Autowired
    private EtapaObraService EtapaObraService;

    @Autowired
    private UtilService UtilService;

    @Autowired
    private ClienteService clienteService;

    public Obra findById(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        Optional<Obra> obra = this.obraRepository.findById(id);
        return obra.orElseThrow(() -> new ObjectNotFoundException(
                "Obra não encontrada! Id: " + id + ", Tipo: " + Obra.class.getName()
        ));
    }

    public Page<ObraSearchProjection> findAll(String search, Pageable pageable) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity)) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        if (pageable == null || pageable.isUnpaged()) {
            pageable = PageRequest.of(0, 10, Sort.by("id").descending());
        }
        System.out.println("search: " + search);
        Page<ObraSearchProjection> obras = this.obraRepository.findByNomeContainingIgnoreCase(search, pageable);
        return obras;
    }

    @Transactional
    public Obra create(Obra obj) {
        
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.ADMIN))) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }
        obj.setDataLancamento(this.UtilService.getTodayDate());

        List<EtapaObra> etapas = new ArrayList<>();

        obj.setId(null);
        obj = this.obraRepository.save(obj);

            Cliente client = this.clienteService.findById(obj.getCliente().getId());
            obj.setCliente(client);
//
        for (EtapaObra etapaObra : obj.getEtapa()) {
                etapaObra.setObra(obj);
                etapas.add(this.EtapaObraService.create(etapaObra));
        }

        obj.setEtapa(etapas);

        obj = this.obraRepository.save(obj);

        return obj;
    }

    @Transactional
    public Obra update(Obra obj) {

        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.SUPERADMIN))) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }

        List<EtapaObra> etapas = new ArrayList<>(); 
        Obra newObj = this.findById(obj.getId());
        newObj.setNome(obj.getNome());
        newObj.setDescription(obj.getDescription());
        newObj.setDataInicio(obj.getDataInicio());
        newObj.setDataPrevista(obj.getDataPrevista());
        newObj.setDataTermino(obj.getDataTermino());
        newObj.setCodigoUPE(obj.getCodigoUPE());
        newObj.setCentroCusto(obj.getCentroCusto());
        newObj.setCliente(obj.getCliente());

        for (EtapaObra etapaObra : obj.getEtapa()) {
            etapaObra.setObra(newObj);
            if (etapaObra.getId() == 0) {
                etapas.add(this.EtapaObraService.create(etapaObra));
            } else {
                etapas.add(this.EtapaObraService.update(etapaObra));
            }
        } 

        newObj.setEtapa(etapas);

        
        return this.obraRepository.save(newObj);
    }

    @Transactional
    public void delete(Long id) {
        UserSpringSecurity userSpringSecurity = UserService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !(userSpringSecurity.hasRole(ProfileEnum.ADMIN))) {
            throw new AuthorizationDeniedException("Acesso negado!");
        }
        findById(id);
        try {
            this.obraRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir esta obra!");
        }
    }

}
