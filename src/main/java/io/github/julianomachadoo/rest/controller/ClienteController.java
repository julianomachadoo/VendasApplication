package io.github.julianomachadoo.rest.controller;

import io.github.julianomachadoo.domain.entity.Cliente;
import io.github.julianomachadoo.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/clientes/")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteController( ClienteRepository clienteRespository) {
        this.clienteRepository = clienteRespository;
    }

    @GetMapping("{id}")
    public Cliente getClienteById( @PathVariable Integer id ) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PostMapping
    @ResponseStatus( CREATED )
    public Cliente save ( @RequestBody @Valid Cliente cliente ) {

        return clienteRepository.save(cliente);
        }

    @DeleteMapping("{id}")
    @ResponseStatus( NO_CONTENT )
    public void delete( @PathVariable Integer id ) {
        clienteRepository.findById(id)
                .map( cliente -> {
                    clienteRepository.delete( cliente );
                    return cliente;
                })
                .orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Cliente não encontrado" ) );

        }

    @PutMapping("{id}")
    @ResponseStatus( NO_CONTENT )
    public void update(@PathVariable Integer id, @RequestBody @Valid Cliente cliente) {

        clienteRepository
                .findById(id)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clienteRepository.save(cliente);
                    return ResponseEntity.noContent().build();
                }).orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Cliente não encontrado" ) );
    }

    @GetMapping
    public List<Cliente> find( Cliente filtro ) {
        ExampleMatcher matcher = ExampleMatcher
                                        .matching()
                                        .withIgnoreCase()
                                        .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );

        Example<Cliente> example = Example.of(filtro, matcher);
        return  clienteRepository.findAll(example);
    }
}
