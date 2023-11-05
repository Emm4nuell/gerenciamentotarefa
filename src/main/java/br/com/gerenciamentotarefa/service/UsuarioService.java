package br.com.gerenciamentotarefa.service;

import br.com.gerenciamentotarefa.dto.UsuarioDto;
import br.com.gerenciamentotarefa.model.Usuario;
import br.com.gerenciamentotarefa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    //@Transactional
    public Usuario create(UsuarioDto dto) {
        Usuario usuario = UsuarioDto.toUsuario(dto);
        Optional<Usuario>  opt = repository.findByCpf(usuario.getCpf());
        if (opt.isPresent()){
            throw new NullPointerException("Usuario já esta cadastrado no sistema");
        }
        return repository.save(usuario);
    }

    public List<Usuario> findAll() {
        List<Usuario> usuarios = repository.findAll();
        return usuarios;
    }

    public Usuario findById(Long id) {
        Optional<Usuario> opt = repository.findById(id);
        opt.orElseThrow(() -> new NullPointerException("Usuario não encontrado!"));
        return opt.get();
    }

    public Usuario update(Long id, UsuarioDto dto) {
        Optional<Usuario> opt = repository.findById(id);
        opt.orElseThrow(() -> new NullPointerException("Usuario não encontrado!"));

        Usuario model = UsuarioDto.toUsuario(dto);
        model.setId(id);

        return repository.save(model);
    }

    public void delete(Long id) {
        Optional<Usuario> opt = repository.findById(id);
        opt.orElseThrow(() -> new NullPointerException("Usuario não encontrado!"));
        repository.deleteById(id);
    }
}
