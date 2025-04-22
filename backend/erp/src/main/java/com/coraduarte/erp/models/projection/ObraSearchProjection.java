package com.coraduarte.erp.models.projection;

import com.coraduarte.erp.models.Cliente;

public interface ObraSearchProjection {

    public Long getId();

    public String getNome();
    public Cliente getCliente();

    public String getDataLancamento();
    
    public String getCodigoUPE();
    public String getCentroCusto();

    public Integer getStatus();

}