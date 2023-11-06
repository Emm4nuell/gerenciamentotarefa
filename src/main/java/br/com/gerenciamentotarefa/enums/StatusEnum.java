package br.com.gerenciamentotarefa.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {

    INICIADO(0, "INICIADO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");

    private Integer id;
    private String status;

    StatusEnum(Integer id, String status){
        this.id = id;
        this.status = status;
    }
}
