package br.com.gerenciamentotarefa.service;

import br.com.gerenciamentotarefa.dto.AuthDto;
import br.com.gerenciamentotarefa.dto.TokenDto;
import br.com.gerenciamentotarefa.dto.UsuarioDto;
import br.com.gerenciamentotarefa.jwt.JwtService;
import br.com.gerenciamentotarefa.model.Usuario;
import br.com.gerenciamentotarefa.repository.TarefaRepository;
import br.com.gerenciamentotarefa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UsuarioService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @Autowired
    private JwtService jwtService;
    private String url = "http://206.42.51.75:8090/usuario/chaveativacao/";

    @Transactional
    public Usuario create(UsuarioDto dto) {
        Usuario usuario = UsuarioDto.toUsuario(dto);
        Optional<Usuario>  opt = repository.findByCpf(usuario.getCpf());

        if (opt.isPresent()){
            throw new NullPointerException("Usuario já esta cadastrado no sistema");
        }

        String chave = chaveValidacao();
        kafkaTemplate.send("topic-1", url.concat(chave));
        usuario.setStatus(false);
        usuario.setChaveativacao(chave);
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
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

    public TokenDto authenticar(AuthDto dto){

        try {
//            String chave = chaveValidacao();
//            kafkaTemplate.send("topic-1", chave);
            Usuario usuario = AuthDto.toUsuario(dto);
            UserDetails details = userDetailsServiceImp.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            TokenDto tokenDto = new TokenDto();
            tokenDto.setToken(token);
            tokenDto.setUsuario(usuario.getEmail());
            return tokenDto;
        }catch (UsernameNotFoundException u){
            throw new UsernameNotFoundException("Usuário ou senha inválida!");
        }
    }



    //Gerado de chave para ativação
    protected String chaveValidacao(){
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int x = 0 ; x < 5 ; x++){
            int index = random.nextInt(caracteres.length());
            sb.append(caracteres.charAt(index));
        }
        return sb.toString();
    }

    public void ativacao(String chave) {
        Optional<Usuario> opt = repository.findByChaveativacao(chave);
        if(opt.isPresent()){
            Usuario usuario = opt.get();
            usuario.setId(usuario.getId());
            usuario.setChaveativacao("");
            usuario.setStatus(true);
            repository.save(usuario);
        }else{
            throw new NullPointerException("Chave de ativação inválida!");
        }
    }
}
