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
    //@Column(unique = true)
    private String cpf;
    //@Column(unique = true)
    private String email;
    private String contato;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date datanascimento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate datacadastro;
    private String senha;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Tarefa> tarefas = new ArrayList<>();
}
