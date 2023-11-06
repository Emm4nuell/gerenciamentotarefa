package br.com.gerenciamentotarefa.dto;

import br.com.gerenciamentotarefa.enums.StatusEnum;
import br.com.gerenciamentotarefa.model.Tarefa;
import br.com.gerenciamentotarefa.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefaDto {

    private Long id;
    @NotBlank(message = "Campo nome da TAREFA é obrigatório")
    private String nometarefa;
    private String descricao;
    private Date datavencimento;
    private LocalDate datacriacao;

    /*Iniciado, processando, cancelado, concluído*/
    /*Tera que ser modificado para enum*/
    private Integer status;
    private UsuarioDto usuario;

    public static Tarefa toTarefa(TarefaDto dto){
        Usuario usuario = UsuarioDto.toUsuario(dto.getUsuario());
        Tarefa tarefa = new Tarefa();

        tarefa.setId(dto.getId());
        tarefa.setNometarefa(dto.getNometarefa());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setDatavencimento(dto.getDatavencimento());
        tarefa.setDatacriacao(dto.getDatacriacao());
        tarefa.setStatus(dto.getStatus());
        tarefa.setUsuario(usuario);
        return tarefa;
    }

    public static TarefaDto toTarefaDto(Tarefa tarefa){
        TarefaDto dto = new TarefaDto();
        dto.id = tarefa.getId();
        dto.nometarefa = tarefa.getNometarefa();
        dto.descricao = tarefa.getDescricao();
        dto.datavencimento = tarefa.getDatavencimento();
        dto.datacriacao = tarefa.getDatacriacao();
        dto.status = tarefa.getStatus();
        return dto;
    }
}
