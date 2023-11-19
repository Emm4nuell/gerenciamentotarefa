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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDate datacriacao;
    private LocalDate datarecebimento;
    private LocalDate dataconcluido;

    /*Iniciado, processando, cancelado, concluído*/
    /*Tera que ser modificado para enum*/
    private Integer status;
    private String observacao;
    private UsuarioDto usuario;

    public static Tarefa toTarefa(TarefaDto dto){
        Usuario usuario = UsuarioDto.toUsuario(dto.getUsuario());
        Tarefa tarefa = new Tarefa();

        tarefa.setId(dto.getId());
        tarefa.setNometarefa(dto.getNometarefa());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setDatacriacao(dto.getDatacriacao());
        tarefa.setDatarecebimento(dto.getDatarecebimento());
        tarefa.setDataconcluido(dto.getDataconcluido());
        tarefa.setObservacao(dto.getObservacao());
        tarefa.setStatus(dto.getStatus());
        tarefa.setUsuario(usuario);
        return tarefa;
    }

    public static TarefaDto toTarefaDto(Tarefa tarefa){
        SimpleDateFormat formatt = new SimpleDateFormat("dd/MM/yyyy");
        TarefaDto dto = new TarefaDto();
        dto.id = tarefa.getId();
        dto.nometarefa = tarefa.getNometarefa();
        dto.descricao = tarefa.getDescricao();
        dto.datacriacao = tarefa.getDatacriacao();
        dto.datarecebimento = tarefa.getDatarecebimento();
        dto.dataconcluido = tarefa.getDataconcluido();
        dto.observacao = tarefa.getObservacao();
        dto.status = tarefa.getStatus();
        return dto;
    }
}
