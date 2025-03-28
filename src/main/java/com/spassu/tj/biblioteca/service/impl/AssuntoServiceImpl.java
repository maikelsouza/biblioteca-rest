package com.spassu.tj.biblioteca.service.impl;

import com.spassu.tj.biblioteca.dto.AssuntoDTO;
import com.spassu.tj.biblioteca.model.Assunto;
import com.spassu.tj.biblioteca.repository.AssuntoRepository;
import com.spassu.tj.biblioteca.service.AssuntoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AssuntoServiceImpl implements AssuntoService {

    @Autowired
    private AssuntoRepository assuntoRepository;

    @Override
    public Assunto criar(Assunto assunto) {
        try {
            return assuntoRepository.save(assunto);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro ao salvar Assunto: possível violação de integridade.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro desconhecido ao salvar Assunto.", e);
        }
    }

    @Override
    @Transactional
    public Assunto atualizar(Long id, AssuntoDTO assuntoDTO) {
        try {
            Assunto assunto = this.buscarPorId(id);
            assunto.setDescricao(assuntoDTO.getDescricao());
            return this.criar(assunto);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro ao atualizar Assunto: possível violação de integridade.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro desconhecido ao atualizar Assunto.", e);
        }
    }

    public List<Assunto> buscarTodos() {
        try {
            List<Assunto> assuntos = assuntoRepository.findAll(Sort.by(Sort.Order.asc("descricao")));
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

    @Override
    public void apagarPorId(Long id) {
        if (!assuntoRepository.existsById(id)) {
            throw new RuntimeException("Assunto não encontrado para o ID: " + id);
        }
        try {
            assuntoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Não é possível excluir o Assunto, pois ele está referenciado em algum livro.", e);
        }
    }

}

