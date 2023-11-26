package br.com.gerenciamentotarefa.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class TarefaCreateDto extends  TarefaDto{
    private Set<UsuarioDto> usuariodtos = new HashSet<>();

}
