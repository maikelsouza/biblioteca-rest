package com.spassu.tj.biblioteca.service.impl;

import com.spassu.tj.biblioteca.controller.AssuntoController;
import com.spassu.tj.biblioteca.model.Assunto;
import com.spassu.tj.biblioteca.repository.AssuntoRepository;
import com.spassu.tj.biblioteca.service.AssuntoService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AssuntoServiceImpl implements AssuntoService {

    @Autowired
    private AssuntoRepository assuntoRepository;

    @Override
    public void criar(Assunto assunto) {
        try {
            assuntoRepository.save(assunto);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro ao salvar Assunto: possível violação de integridade.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro desconhecido ao salvar Assunto.", e);
        }
    }

    public List<Assunto> buscarTodos() {
        try {
            List<Assunto> assuntos = (List<Assunto>) assuntoRepository.findAll();
            return assuntos.isEmpty() ? Collections.emptyList() : assuntos;
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao buscar todos os Assuntos.", e);
        }
    }

    @Override
    public Assunto buscarPorId(Long id) {
        try {
            return assuntoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Assunto não encontrado para o ID: " + id));
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao buscar Assunto por ID.", e);
        }
    }
}
