package com.spassu.tj.biblioteca.controller;

import com.spassu.tj.biblioteca.model.Assunto;
import com.spassu.tj.biblioteca.service.AssuntoService;
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
@RequestMapping(value = "/api/assuntos")
public class AssuntoController {

    private static final Logger logger = LogManager.getLogger(AssuntoController.class);

    @Autowired
    private final AssuntoService assuntoService;

    @PostMapping()
    public ResponseEntity<Assunto> criar(@RequestBody Assunto assunto) {
        logger.info("Requisição para criar um Assunto.");
        try {
            assuntoService.criar(assunto);
            logger.info("Assunto criado com sucesso com ID: {}", assunto.getCodAs());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erro ao tentar criar um assunto: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Assunto>> buscarTodos() {
        logger.info("Requisição para buscar todos os assuntos.");
        try {
            List<Assunto> assuntos = assuntoService.buscarTodos();
            return new ResponseEntity<>(assuntos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao buscar todos os assuntos: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Assunto> buscarPorId(@PathVariable Long id) {
        logger.info("Requisição para buscar um assunto pelo id.");
        try {
            Assunto assunto = assuntoService.buscarPorId(id);
            return new ResponseEntity<>(assunto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao buscar um assunto pelo id: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


