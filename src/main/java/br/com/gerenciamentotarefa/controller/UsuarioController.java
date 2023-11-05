package br.com.gerenciamentotarefa.controller;

import br.com.gerenciamentotarefa.dto.UsuarioDto;
import br.com.gerenciamentotarefa.model.Usuario;
import br.com.gerenciamentotarefa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAll(){

        List<UsuarioDto> dtos = service.findAll().stream()
                .map(x -> UsuarioDto.toUsuarioDto(x))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    @PostMapping
    public ResponseEntity<UsuarioDto> create(@RequestBody UsuarioDto dto){

        Usuario model = service.create(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .buildAndExpand(model.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

}
