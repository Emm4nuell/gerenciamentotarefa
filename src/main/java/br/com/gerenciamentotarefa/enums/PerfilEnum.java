package br.com.gerenciamentotarefa.enums;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public enum PerfilEnum {

    ADMINISTRADOR(1, "ADMINISTRADOR"), GERENTE(2, "GERENTE"), SUPERVISOR(3, "SUPERVISOR"), FUNCIONARIO(4, "FUNCIONARIO");

    private String perfil;
    private Integer id;


    PerfilEnum(Integer id, String perfil) {
        this.id = id;
        this.perfil = perfil;
    }

    public static PerfilEnum toPerfil(Integer id){

        if(id == null){
            return PerfilEnum.FUNCIONARIO;
        }

        for(PerfilEnum x : PerfilEnum.values()){
            if (id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil inválido!");
    }
}
