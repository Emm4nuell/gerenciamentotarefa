package br.com.gerenciamentotarefa.enums;

import lombok.Getter;

@Getter
public enum PrioridadeEnum {

    BAIXA(1, "BAIXA"), MEDIA(2, "MÉDIA"), ALTA(3, "ALTA");

    private Integer id;
    private String prioridade;

    PrioridadeEnum(int i, String prioridade) {
        this.id = id;
        this.prioridade = prioridade;
    }

    public PrioridadeEnum toPrioridadeEnum(Integer id){

        if(id == null){
            return PrioridadeEnum.BAIXA;
        }

        for (PrioridadeEnum p : PrioridadeEnum.values()){
            if(id.equals(p.getId())){
                return p;
            }
        }

        throw new IllegalArgumentException("Prioridade inválida!");
    }
}
