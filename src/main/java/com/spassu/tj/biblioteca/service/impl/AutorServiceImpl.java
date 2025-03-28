package com.spassu.tj.biblioteca.service.impl;

import com.spassu.tj.biblioteca.dto.AutorDTO;
import com.spassu.tj.biblioteca.model.Autor;
import com.spassu.tj.biblioteca.repository.AutorRepository;
import com.spassu.tj.biblioteca.service.AutorService;
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
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public Autor criar(Autor autor) {
        try {
            return autorRepository.save(autor);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro ao salvar Autor: possível violação de integridade.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro desconhecido ao salvar Autor.", e);
        }
    }

    @Override
    @Transactional
    public Autor atualizar(Long id, AutorDTO autorDTO) {
        try {
            Autor autor = this.buscarPorId(id);
            autor.setNome(autorDTO.getNome());
            return this.criar(autor);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro ao atualizar Autor: possível violação de integridade.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro desconhecido ao atualizar Autor.", e);
        }
    }

    public List<Autor> buscarTodos() {
        try {
            List<Autor> autores = autorRepository.findAll(Sort.by(Sort.Order.asc("nome")));
            return autores.isEmpty() ? Collections.emptyList() : autores;
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao buscar todos os Autores.", e);
        }
    }

    @Override
    public Autor buscarPorId(Long id) {
        try {
            return autorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado para o ID: " + id));
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao buscar Autor por ID.", e);
        }
    }

    public void apagarPorId(Long id) {
        if (!autorRepository.existsById(id)) {
            throw new RuntimeException("Autor não encontrado para o ID: " + id);
        }
        try {
            autorRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Não é possível excluir o Autor, pois ele está referenciado em algum livro.", e);
        }
    }

}
