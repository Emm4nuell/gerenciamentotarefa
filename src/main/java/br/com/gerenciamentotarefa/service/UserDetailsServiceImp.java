package br.com.gerenciamentotarefa.service;

import br.com.gerenciamentotarefa.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UsuarioService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!username.equals("eduardo")){
            throw new UsernameNotFoundException("Usuário inválido!");
        }


        return User
                .builder()
                .username("eduardo")
                .password(passwordEncoder.encode("123"))
                .roles("USER")
                .build();
    }
}
