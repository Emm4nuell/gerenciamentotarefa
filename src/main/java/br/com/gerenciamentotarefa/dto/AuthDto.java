package br.com.gerenciamentotarefa.dto;

import br.com.gerenciamentotarefa.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDto {

    private String usuario;
    private String senha;

    public static Usuario toUsuario(AuthDto dto){
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getUsuario());
        usuario.setSenha(dto.getSenha());
        return usuario;
    }

}
