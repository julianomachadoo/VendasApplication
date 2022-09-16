package io.github.julianomachadoo.rest.controller;

import io.github.julianomachadoo.domain.entity.Produto;
import io.github.julianomachadoo.domain.repository.ProdutoRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos/")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController ( ProdutoRepository produtos ) { this.produtoRepository = produtos; }

    @GetMapping("{id}")
    public Produto getProdutobyId(@PathVariable Integer id ) {
        return produtoRepository
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto save ( @RequestBody @Valid Produto produto ) { return produtoRepository.save(produto); }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable Integer id) {
        produtoRepository.findById(id)
                .map( produto -> {
                    produtoRepository.delete(produto);
                    return produto;
                }).orElseThrow(  () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado") );
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Integer id,
                       @RequestBody @Valid Produto produto) {
        produtoRepository
                .findById(id)
                .map( produtoExistente -> {
                    produto.setId(produtoExistente.getId());
                    produtoRepository.save(produto);
                    return ResponseEntity.noContent().build();
                }).orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Produto não encontrado" ) );
    }

    @GetMapping
    public List<Produto> find(Produto filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );

        Example<Produto> example = Example.of(filtro, matcher);
        return  produtoRepository.findAll(example);
    }

}

