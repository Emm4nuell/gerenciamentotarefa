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
    @NotBlank(message = "Campo CPF é obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;
    @NotBlank(message = "Campo EMAIL é obrigátorio")
    @Email(message = "EMAIL inválido")
    private String email;
    private String contato;
    private Date datanascimento;
    private LocalDate datacadastro;
    @NotBlank(message = "Campo SENHA é obrigatório")
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
