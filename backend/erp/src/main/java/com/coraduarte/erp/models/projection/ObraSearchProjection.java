package com.coraduarte.erp.models.projection;

import java.sql.Date;

import com.coraduarte.erp.models.enums.Status;

public interface ObraSearchProjection {

    public Long getId();

    public String getNome();

    public Date getDataLancamento();

    public Status getStatus();

}