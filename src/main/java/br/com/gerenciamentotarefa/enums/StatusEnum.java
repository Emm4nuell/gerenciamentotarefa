package br.com.gerenciamentotarefa.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {

    INICIADO(0, "INICIADO"), ANDAMENTO(1, "ANDAMENTO"), CONCLUIDO(2, "CONCLUIDO"), CANCELADO(3, "CANCELADO");

    private Integer id;
    private String status;

    StatusEnum(Integer id, String status){
        this.id = id;
        this.status = status;
    }

    public static StatusEnum toStatus(Integer id){
        if (id == null){
            return StatusEnum.INICIADO;
        }

        for (StatusEnum status: StatusEnum.values()){
            if (id.equals(status.getId())){
                return status;
            }
        }
        throw new IllegalArgumentException("Status inválido");
    }
}
