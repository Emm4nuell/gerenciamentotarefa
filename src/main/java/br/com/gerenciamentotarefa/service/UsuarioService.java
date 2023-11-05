package br.com.gerenciamentotarefa.service;

import br.com.gerenciamentotarefa.dto.UsuarioDto;
import br.com.gerenciamentotarefa.model.Usuario;
import br.com.gerenciamentotarefa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public Usuario create(UsuarioDto dto) {
        Usuario usuario = UsuarioDto.toUsuario(dto);
        Optional<Usuario>  opt = repository.findByCpf(usuario.getCpf());
        repository.save(usuario);
        return usuario;
    }

    public List<Usuario> findAll() {
        List<Usuario> usuarios = repository.findAll();
        return usuarios;
    }
}
