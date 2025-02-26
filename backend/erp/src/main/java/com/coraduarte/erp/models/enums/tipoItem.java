package com.coraduarte.erp.models.enums;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public enum tipoItem {

     SERVICO(1),
     MATERIAL(2);

     private Integer code;

     public static tipoItem toEnum(Integer code){
          if (Objects.isNull(code))
              return null;
     
          for(tipoItem x : tipoItem.values()){
            System.out.println(x.getCode());
              if (x.getCode().equals(code))
              return x;
          }
     
          throw new IllegalArgumentException("Invalid Code tipoItem");
     
      }
}


