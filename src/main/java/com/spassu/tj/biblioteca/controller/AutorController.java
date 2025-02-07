package com.spassu.tj.biblioteca.controller;


import com.spassu.tj.biblioteca.dto.AutorDTO;
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
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/autores")
public class AutorController {

    private static final Logger logger = LogManager.getLogger(AutorController.class);

    @Autowired
    private final AutorService autorService;

    @PostMapping()
    public ResponseEntity<Void> criar(@RequestBody AutorDTO autorDTO) {
        logger.info("Requisição para criar um Autor.");
        try {
            Autor autor = autorService.criar(AutorDTO.convertToEntity(autorDTO));
            logger.info("Autor criado com sucesso com ID: {}", autor.getCodAu());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Erro ao tentar criar um autor: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody AutorDTO autorDTO) {
        logger.info("Requisição para atualizar o Autor com ID: {}", id);
        try {
            Autor autorAtualizado = autorService.atualizar(id, autorDTO);
            logger.info("Autor atualizado com sucesso com ID: {}", autorAtualizado.getCodAu());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            logger.warn("Autor com ID {} não encontrado.", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Erro ao tentar atualizar o autor: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> buscarTodos() {
        logger.info("Requisição para buscar todos os autores.");
        try {
            List<Autor> autores = autorService.buscarTodos();
            List<AutorDTO> autorDTOs = AutorDTO.convertToDTO(autores);
            return new ResponseEntity<>(autorDTOs, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao buscar todos os autores: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AutorDTO> buscarPorId(@PathVariable Long id) {
        logger.info("Requisição para buscar um autor pelo id.");
        try {
            Autor autor = autorService.buscarPorId(id);
            AutorDTO autorDTO = AutorDTO.convertToDTO(autor);
            return new ResponseEntity<>(autorDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao buscar um autor pelo id: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> apagarPorId(@PathVariable Long id) {
        logger.info("Requisição para deletar um autor pelo id.");
        try {
            autorService.apagarPorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Erro ao deletar um autor pelo id: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
