package br.com.gerenciamentotarefa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @NotBlank(message = "Campo CPF é obrigatório")
    @CPF(message = "CPF inválido")
    @Column(unique = true)
    private String cpf;
    @NotBlank(message = "Campo EMAIL é obrigátorio")
    @Email(message = "EMAIL inválido")
    private String email;
    private String contato;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date datanascimento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate datacadastro;
    @NotBlank(message = "Campo SENHA é obrigatório")
    private String senha;

    @OneToMany(mappedBy = "usuario")
    private List<Tarefa> tarefas = new ArrayList<>();
}
