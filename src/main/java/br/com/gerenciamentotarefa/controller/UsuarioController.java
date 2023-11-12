package br.com.gerenciamentotarefa.controller;

import br.com.gerenciamentotarefa.dto.AuthDto;
import br.com.gerenciamentotarefa.dto.TokenDto;
import br.com.gerenciamentotarefa.dto.UsuarioDto;
import br.com.gerenciamentotarefa.model.Usuario;
import br.com.gerenciamentotarefa.service.UserDetailsServiceImp;
import br.com.gerenciamentotarefa.service.UsuarioService;
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
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private UserDetailsServiceImp detailsServiceImp;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAll(){

        List<UsuarioDto> dtos = service.findAll().stream()
                .map(x -> UsuarioDto.toUsuarioDto(x))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDto> findById(@PathVariable Long id){
        UsuarioDto dto = UsuarioDto.toUsuarioDto(service.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDto> create(@Valid @RequestBody UsuarioDto dto){

        UsuarioDto usuariodto = UsuarioDto.toUsuarioDto(service.create(dto));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(usuariodto.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/auth")
    public ResponseEntity<String> autenticar(@Valid @RequestBody AuthDto dto){
        TokenDto tokenDto = service.authenticar(dto);
        String token = "Bearer ".concat(tokenDto.getToken());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioDto> update(@PathVariable Long id, @RequestBody UsuarioDto dto){

        UsuarioDto usuariodto = UsuarioDto.toUsuarioDto(service.update(id, dto));

        return ResponseEntity.status(HttpStatus.OK).body(usuariodto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete( @PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
