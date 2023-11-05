package br.com.gerenciamentotarefa.controller;

import br.com.gerenciamentotarefa.dto.TarefaDto;
import br.com.gerenciamentotarefa.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService service;

    @GetMapping
    public ResponseEntity<List<TarefaDto>> findAll(){
        List<TarefaDto> tarefas = service.findAll().stream()
                .map(x -> TarefaDto.toTarefaDto(x)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(tarefas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TarefaDto> findById(@PathVariable Long id){
        TarefaDto dto = TarefaDto.toTarefaDto(service.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping
    public ResponseEntity<TarefaDto> create(@Valid @RequestBody TarefaDto dto){
        TarefaDto tarefadto = TarefaDto.toTarefaDto(service.create(dto));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(tarefadto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TarefaDto> update(@PathVariable Long id, @Valid @RequestBody TarefaDto dto){
        TarefaDto tarefadto = TarefaDto.toTarefaDto(service.update(id, dto));
        return ResponseEntity.status(HttpStatus.OK).body(tarefadto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
