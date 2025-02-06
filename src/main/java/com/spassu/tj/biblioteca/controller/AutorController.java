package com.spassu.tj.biblioteca.controller;


import com.spassu.tj.biblioteca.model.Autor;
import com.spassu.tj.biblioteca.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/autores")
public class AutorController {

    private static final Logger logger = LogManager.getLogger(AutorController.class);

    @Autowired
    private final AutorService autorService;

    @PostMapping()
    public ResponseEntity<Autor> criar(@RequestBody Autor autor) {
        logger.info("Requisição para criar um Autor.");
        try {
            autorService.criar(autor);
            logger.info("Autor criado com sucesso com ID: {}", autor.getCodAu());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erro ao tentar criar um autor: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Autor>> buscarTodos() {
        logger.info("Requisição para buscar todos os autores.");
        try {
            List<Autor> autores = autorService.buscarTodos();
            return new ResponseEntity<>(autores, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao buscar todos os autores: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Autor> buscarPorId(@PathVariable Long id) {
        logger.info("Requisição para buscar um autor pelo id.");
        try {
            Autor autor = autorService.buscarPorId(id);
            return new ResponseEntity<>(autor, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao buscar um autor pelo id: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
