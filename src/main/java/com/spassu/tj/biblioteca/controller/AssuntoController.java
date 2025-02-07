package com.spassu.tj.biblioteca.controller;

import com.spassu.tj.biblioteca.dto.AssuntoDTO;
import com.spassu.tj.biblioteca.model.Assunto;
import com.spassu.tj.biblioteca.service.AssuntoService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/assuntos")
public class AssuntoController {

    private static final Logger logger = LogManager.getLogger(AssuntoController.class);

    @Autowired
    private final AssuntoService assuntoService;

    @PostMapping()
    public ResponseEntity<Void> criar(@RequestBody AssuntoDTO assuntoDTO) {
        logger.info("Requisição para criar um Assunto.");
        try {
            Assunto assunto = assuntoService.criar(AssuntoDTO.convertToEntity(assuntoDTO));
            logger.info("Assunto criado com sucesso com ID: {}", assunto.getCodAs());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erro ao tentar criar um assunto: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody AssuntoDTO assuntoDTO) {
        logger.info("Requisição para atualizar o Assunto com ID: {}", id);
        try {
            Assunto assuntoAtualizado = assuntoService.atualizar(id, assuntoDTO);
            logger.info("Assunto atualizado com sucesso com ID: {}", assuntoAtualizado.getCodAs());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            logger.warn("Assunto com ID {} não encontrado.", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Erro ao tentar atualizar o autor: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping
    public ResponseEntity<List<AssuntoDTO>> buscarTodos() {
        logger.info("Requisição para buscar todos os assuntos.");
        try {
            List<Assunto> assuntos = assuntoService.buscarTodos();
            List<AssuntoDTO> assuntoDTOS = AssuntoDTO.convertToDTO(assuntos);
            return new ResponseEntity<>(assuntoDTOS, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao buscar todos os assuntos: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AssuntoDTO> buscarPorId(@PathVariable Long id) {
        logger.info("Requisição para buscar um assunto pelo id.");
        try {
            Assunto assunto = assuntoService.buscarPorId(id);
            AssuntoDTO assuntoDTO = AssuntoDTO.convertToDTO(assunto);
            return new ResponseEntity<>(assuntoDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao buscar um assunto pelo id: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> apagarPorId(@PathVariable Long id) {
        logger.info("Requisição para deletar um assunto pelo id.");
        try {
            assuntoService.apagarPorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Erro ao deletar um assunto pelo id: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


