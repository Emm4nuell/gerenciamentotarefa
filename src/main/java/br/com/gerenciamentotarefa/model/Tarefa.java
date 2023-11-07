package br.com.gerenciamentotarefa.model;

import br.com.gerenciamentotarefa.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
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

    //@JsonFormat(pattern = "dd/MM/yyyy")
    private Date datavencimento;

    //@JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate datacriacao;

    /*Iniciado, processando, cancelado, concluído*/
    /*Tera que ser modificado para enum*/
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
