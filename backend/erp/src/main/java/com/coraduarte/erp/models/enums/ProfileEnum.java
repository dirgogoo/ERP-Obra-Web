package com.coraduarte.erp.models.enums;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ProfileEnum {

    SUPERADMIN(1,"ROLE_SUPERADMIN"),
    ADMIN(2,"ROLE_ADMIN"),
    USER(3,"ROLE_USER");

    private Integer code;
    private String description;

    public static ProfileEnum toProfileEnum(Integer code){
        if (Objects.isNull(code))
            return null;

        for(ProfileEnum x : ProfileEnum.values()){
            if (x.equals(code))
            return x;
        }

        throw new IllegalArgumentException("Invalid Code ProfileEnum");

    }

    


}
