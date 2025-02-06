package com.spassu.tj.biblioteca.controller;

import com.spassu.tj.biblioteca.model.Livro;
import com.spassu.tj.biblioteca.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/livros")
public class LivroController {

    private static final Logger logger = LogManager.getLogger(LivroController.class);

    @Autowired
    private final LivroService livroService;


    @PostMapping()
    public ResponseEntity<Livro> criar(@RequestBody Livro livro) {
        logger.info("Requisição para criar um Livro.");
        try {
            livroService.criar(livro);
            logger.info("Livro criado com sucesso com ID: {}", livro.getCodL());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao tentar criar um livro: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Livro>> buscarTodos() {
        logger.info("Requisição para buscar todos os livros.");
        try {
            List<Livro> livros = livroService.buscarTodos();
            HttpHeaders headers = getHttpHeaders();
            return new ResponseEntity<>(livros, headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Ocorreu um erro  ao tentar buscar todos os livros: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        logger.info("Requisição para buscar um livro pelo id.");
        try {
            Livro livro = livroService.buscarPorId(id);
            HttpHeaders headers = getHttpHeaders();
            return new ResponseEntity<>(livro, headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao tentar buscar um livro pelo id: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "some-uri");
        return headers;
    }

//    private HttpHeaders loadUri(Livro livro) {
//        final String uri = ServletUriComponentsBuilder
//                .fromCurrentServletMapping()
//                .path("/baskets/{id}")
//                .buildAndExpand(livro.getCodL())
//                .toString();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Location", uri);
//        return headers;
//    }
}
