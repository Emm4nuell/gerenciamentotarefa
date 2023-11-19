package br.com.gerenciamentotarefa.model;

import br.com.gerenciamentotarefa.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nometarefa;
    private String descricao;

    //@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate datacriacao;

    //@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate datarecebimento;

    //@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataconcluido;

    /*Iniciado, processando, cancelado, concluído*/
    /*Tera que ser modificado para enum*/
    private Integer status;

    private String observacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
