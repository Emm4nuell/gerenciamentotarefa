package br.com.gerenciamentotarefa.dto;

import br.com.gerenciamentotarefa.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDto {

    @NotBlank(message = "Campo usuário é obrigatorio!")
    private String usuario;
    @NotBlank(message = "Campo senha é obrigatório!")
    private String senha;

    public static Usuario toUsuario(AuthDto dto){
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getUsuario());
        usuario.setSenha(dto.getSenha());
        return usuario;
    }

}
