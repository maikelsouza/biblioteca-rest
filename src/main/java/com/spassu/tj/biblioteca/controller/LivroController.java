package com.spassu.tj.biblioteca.controller;

import com.spassu.tj.biblioteca.dto.LivroDTO;
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

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/livros")
public class LivroController {

    private static final Logger logger = LogManager.getLogger(LivroController.class);

    @Autowired
    private final LivroService livroService;


    @PostMapping()
    public ResponseEntity<Void> criar(@RequestBody LivroDTO livroDTO) {
        logger.info("Requisição para criar um Livro.");
        try {
            Livro livro = livroService.criar(LivroDTO.convertToEntity(livroDTO));
            logger.info("Livro criado com sucesso com ID: {}", livro.getCodL());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao tentar criar um livro: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody LivroDTO livroDTO) {
        logger.info("Requisição para atualizar o Livro com ID: {}", id);
        try {
            Livro livroAtualizado = livroService.atualizar(id, livroDTO);
            logger.info("Livro atualizado com sucesso com ID: {}", livroAtualizado.getCodL());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            logger.warn("Livro com ID {} não encontrado.", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Erro ao tentar atualizar o livro: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping
    public ResponseEntity<List<LivroDTO>> buscarTodos() {
        logger.info("Requisição para buscar todos os livros.");
        try {
            List<Livro> livros = livroService.buscarTodos();
            List<LivroDTO> livroDTOS = LivroDTO.convertToDTO(livros);
            HttpHeaders headers = getHttpHeaders();
            return new ResponseEntity<>(livroDTOS, headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Ocorreu um erro  ao tentar buscar todos os livros: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LivroDTO> buscarPorId(@PathVariable Long id) {
        logger.info("Requisição para buscar um livro pelo id.");
        try {
            Livro livro = livroService.buscarPorId(id);
            LivroDTO livroDTO = LivroDTO.convertToDTO(livro);
            HttpHeaders headers = getHttpHeaders();
            return new ResponseEntity<>(livroDTO, headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao tentar buscar um livro pelo id: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> apagarPorId(@PathVariable Long id) {
        logger.info("Requisição para deletar um livro pelo id.");
        try {
            livroService.apagarPorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Erro ao deletar um livro pelo id: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "some-uri");
        return headers;
    }

}
