package com.coraduarte.erp.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public enum Status {
    NAOINICIADO(1),
    ANDAMENTO( 2),
    CONCLUIDO( 3);

    private Integer code;

    public static Status toEnum(Integer code){
        if (code == null)
            return null;
    
        for(Status x : Status.values()){
            if (x.getCode().equals(code))
            return x;
        }
    
        throw new IllegalArgumentException("Invalid Code Status");
    
    }

}