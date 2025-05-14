package com.coraduarte.erp.models.enums;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ProfileEnum {

    SUPERADMIN(1,"ROLE_USER"),
    ADMIN(2,"ROLE_ADMIN"),
    USER(3,"ROLE_SUPERADMIN");

    private Integer code;
    private String description;

    public Integer getCode() {
        return code;
    }

    public static ProfileEnum toProfileEnum(Integer code){
        if (Objects.isNull(code))
            return null;

        for(ProfileEnum x : ProfileEnum.values()){
            if (x.getCode().equals(code))
            return x;
        }

        throw new IllegalArgumentException("Invalid Code ProfileEnum");

    }

    public Object toEnum(String authority) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toEnum'");
    }

    

    


}
