package br.com.gerenciamentotarefa.service;

import br.com.gerenciamentotarefa.dto.TarefaDto;
import br.com.gerenciamentotarefa.model.Tarefa;
import br.com.gerenciamentotarefa.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;
    public List<Tarefa> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Tarefa create(TarefaDto dto) {
        Tarefa tarefa = TarefaDto.toTarefa(dto);
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
        tarefa.setId(id);
        return repository.save(tarefa);
    }

    public void delete(Long id) {
        Optional<Tarefa> opt = repository.findById(id);
        opt.orElseThrow(() -> new NullPointerException("Tarefa não localizada!"));
        repository.deleteById(id);
    }
}
