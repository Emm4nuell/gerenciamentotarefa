package br.com.gerenciamentotarefa.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum PerfilEnum {

    ADMINISTRADOR(1, "ADMINISTRADOR"), GERENTE(2, "GERENTE"), SUPERVISOR(3, "SUPERVISOR"), FUNCIONARIO(4, "FUNCIONARIO");

    private String perfil;
    private Integer id;


    PerfilEnum(Integer id, String perfil) {
        this.id = id;
        this.perfil = perfil;
    }

    public static Integer toPerfil(Integer id){
        for(PerfilEnum x : PerfilEnum.values()){
            if (id.equals(x.getId())){
                return x.getId();
            }
        }
        throw new IllegalArgumentException("Perfil inválido!");
    }
}
