package com.coraduarte.erp.models.projection;

import com.coraduarte.erp.models.Cliente;
import com.coraduarte.erp.models.enums.Status;

public interface ObraSearchProjection {

    public Long getId();

    public String getNome();
    public Cliente getCliente();

    public String getDataLancamento();

    public Status getStatus();

}