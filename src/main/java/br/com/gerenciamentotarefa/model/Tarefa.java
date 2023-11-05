package br.com.gerenciamentotarefa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Campo NOME DA TAREFA é obrigatório")
    private String nometarefa;
    private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date datavencimento;

    /*Iniciado, processando, cancelado, concluído*/
    /*Tera que ser modificado para enum*/
    private String status;
}
