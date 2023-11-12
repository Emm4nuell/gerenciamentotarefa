package br.com.gerenciamentotarefa.service;

import br.com.gerenciamentotarefa.dto.AuthDto;
import br.com.gerenciamentotarefa.enums.PerfilEnum;
import br.com.gerenciamentotarefa.model.Usuario;
import br.com.gerenciamentotarefa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetails autenticar(Usuario usuario){
        UserDetails user = loadUserByUsername(usuario.getEmail());
        boolean issenha=  passwordEncoder.matches(usuario.getSenha(), user.getPassword());

       if (issenha){
           return user;
       }
        throw new UsernameNotFoundException("Usuário ou senha inválida!");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuairo = repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário ou senha inválida!"));
        List<String> roles = usuairo.getPerfis().stream().map(x -> PerfilEnum.toPerfil(x).getPerfil()).collect(Collectors.toList());
        String[] arrayrole = roles.toArray(new String[0]);


        return User
                .builder()
                .username(usuairo.getEmail())
                .password(usuairo.getSenha())
                .roles(arrayrole)
                .build();
    }
}
