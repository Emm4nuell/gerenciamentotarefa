package br.com.gerenciamentotarefa.dto;



import br.com.gerenciamentotarefa.enums.PerfilEnum;
import br.com.gerenciamentotarefa.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    private Long id;
    private String nome;
    //@NotBlank(message = "Campo CPF é obrigatório")
    //@CPF(message = "CPF inválido")
    private String cpf;
    //@NotBlank(message = "Campo EMAIL é obrigátorio")
    //@Email(message = "EMAIL inválido")
    private String email;
    private String contato;
    private Date datanascimento;
    private LocalDate datacadastro;
    //@NotBlank(message = "Campo SENHA é obrigatório")
    private String senha;
    private Set<Integer> perfis = new HashSet<>();
    private List<TarefaDto> tarefas = new ArrayList<>();

    public static UsuarioDto toUsuarioDto(Usuario usuario) {


        List<TarefaDto> tfdto = usuario.getTarefas()
                .stream().map(x -> TarefaDto.toTarefaDto(x)).collect(Collectors.toList());

        UsuarioDto dto = new UsuarioDto();
        dto.id = usuario.getId();
        dto.nome = usuario.getNome();
        dto.cpf = usuario.getCpf();
        dto.email = usuario.getEmail();
        dto.contato = usuario.getContato();
        dto.datanascimento = usuario.getDatanascimento();
        dto.datacadastro = usuario.getDatacadastro();
        dto.senha = usuario.getSenha();
        dto.perfis = usuario.getPerfis();
        dto.tarefas = tfdto;
        return dto;
    }

    public static Usuario toUsuario(UsuarioDto dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setCpf(dto.getCpf());
        usuario.setEmail(dto.getEmail());
        usuario.setContato(dto.getContato());
        usuario.setDatanascimento(dto.getDatanascimento());
        usuario.setDatacadastro(dto.getDatacadastro());
        usuario.setSenha(dto.getSenha());
        usuario.setPerfis(dto.getPerfis().stream().map(x -> PerfilEnum.toPerfil(x)).collect(Collectors.toSet()));
        //usuario.setTarefas(dto.getTarefas());
        return usuario;
    }
}
