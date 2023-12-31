package br.com.gerenciamentotarefa.service;

import br.com.gerenciamentotarefa.dto.TarefaDto;
import br.com.gerenciamentotarefa.dto.UsuarioDto;
import br.com.gerenciamentotarefa.enums.StatusEnum;
import br.com.gerenciamentotarefa.model.Tarefa;
import br.com.gerenciamentotarefa.model.Usuario;
import br.com.gerenciamentotarefa.repository.TarefaRepository;
import br.com.gerenciamentotarefa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    @Autowired
    private UsuarioService service;

    public List<Tarefa> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Tarefa create(TarefaDto dto) {
        Usuario usuario = service.findById(dto.getUsuario().getId());
        Tarefa tarefa = TarefaDto.toTarefa(dto);
        tarefa.setUsuario(usuario);
        tarefa.setDatacriacao(LocalDate.now());
        tarefa.setStatus(StatusEnum.toStatus(dto.getStatus()).getId());
        return repository.save(tarefa);
    }

    public Tarefa findById(Long id) {
        Optional<Tarefa> opt = repository.findById(id);
        opt.orElseThrow(() -> new NullPointerException("Tarefa não localizada!"));
        return opt.get();
    }

    public Tarefa update(Long id, TarefaDto dto) {
        Optional<Tarefa> opt = repository.findById(id);
        opt.orElseThrow(() -> new NullPointerException("Tarefa não localizada!"));
        Tarefa tarefa = TarefaDto.toTarefa(dto);
        System.err.println(tarefa.getUsuario().getId());
        tarefa.setId(id);
        return repository.save(tarefa);
    }

    public void delete(Long id) {
        Optional<Tarefa> opt = repository.findById(id);
        opt.orElseThrow(() -> new NullPointerException("Tarefa não localizada!"));
        repository.deleteById(id);
    }

    public Tarefa updateData(Long id, TarefaDto dto) {

        Tarefa tarefa = findById(id);

        System.out.println(tarefa);

        if(dto.getDatarecebimento() == null){
            tarefa.setDatarecebimento(LocalDate.now());
            tarefa.setStatus(1);
        }else{
            if (dto.getDataconcluido() == null){
                tarefa.setDataconcluido(LocalDate.now());
                tarefa.setStatus(2);
            }
        }
        return repository.save(tarefa);
        //return tarefa;
    }
}
