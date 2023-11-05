package br.com.gerenciamentotarefa.dto;


import br.com.gerenciamentotarefa.model.Tarefa;
import br.com.gerenciamentotarefa.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String contato;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date datanascimento;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate datacadastro;
    private String senha;
    private List<Tarefa> tarefas = new ArrayList<>();

    public static  UsuarioDto toUsuarioDto(Usuario usuario) {
        UsuarioDto dto = new UsuarioDto();
        dto.id = usuario.getId();
        dto.nome = usuario.getNome();
        dto.cpf = usuario.getCpf();
        dto.email = usuario.getEmail();
        dto.contato = usuario.getContato();
        dto.datanascimento = usuario.getDatanascimento();
        dto.datacadastro = usuario.getDatacadastro();
        dto.senha = usuario.getSenha();
        dto.tarefas = usuario.getTarefas();
        return dto;
    }

    public static Usuario toUsuario(UsuarioDto dto){
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setCpf(dto.getCpf());
        usuario.setEmail(dto.getEmail());
        usuario.setContato(dto.getContato());
        usuario.setDatanascimento(dto.getDatanascimento());
        usuario.setDatacadastro(dto.getDatacadastro());
        usuario.setSenha(dto.getSenha());
        usuario.setTarefas(dto.getTarefas());
        return usuario;
    }
}
