package br.com.gerenciamentotarefa.model;

import br.com.gerenciamentotarefa.enums.PerfilEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

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
    //@JsonFormat(pattern = "dd/MM/yyyy")
    private Date datanascimento;
    //@JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate datacadastro;
    private String senha;

    /* Vai ter uma tabela no banco com o nome perfis */
    @ElementCollection(fetch = FetchType.EAGER) // Tera que vier essa lista de perfis do banco de dados obrigatoriamente
    @CollectionTable(name = "PERFIS")
    private Set<Integer> perfis = new HashSet<>();

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Tarefa> tarefas = new ArrayList<>();
}
