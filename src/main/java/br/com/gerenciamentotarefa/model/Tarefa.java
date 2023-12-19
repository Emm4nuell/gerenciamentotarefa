package br.com.gerenciamentotarefa.model;

import br.com.gerenciamentotarefa.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
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

    private Integer prioridade;

    private String observacao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    /*@OneToMany(mappedBy = "tarefa")
    private Set<Usuario> usuarios = new HashSet<>();*/

}
