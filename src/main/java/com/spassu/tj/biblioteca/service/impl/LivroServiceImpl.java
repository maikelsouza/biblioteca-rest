package com.spassu.tj.biblioteca.service.impl;

import com.spassu.tj.biblioteca.dto.LivroDTO;
import com.spassu.tj.biblioteca.model.Livro;
import com.spassu.tj.biblioteca.repository.LivroRepository;
import com.spassu.tj.biblioteca.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LivroServiceImpl implements LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro criar(Livro livro) {
        try {
            return livroRepository.save(livro);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro ao salvar Livro: possível violação de integridade.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro desconhecido ao salvar Livro.", e);
        }
    }

    @Override
    @Transactional
    public Livro atualizar(Long id, LivroDTO livroDTO) {
        try {
            Livro livro = this.buscarPorId(id);
            livro.setTitulo(livroDTO.getTitulo());
            livro.setValor(livroDTO.getValor());
            livro.setEditora(livroDTO.getEditora());
            livro.setEdicao(livroDTO.getEdicao());
            livro.setAnoPublicacao(livroDTO.getAnoPublicacao());
            return this.criar(livro);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro ao atualizar Livro: possível violação de integridade.", e);
        } catch (Exception e) {
            throw new RuntimeException("Erro desconhecido ao atualizar Livro.", e);
        }
    }


    public List<Livro> buscarTodos() {
        try {
            List<Livro> livros = (List<Livro>) livroRepository.findAll();
            return livros.isEmpty() ? Collections.emptyList() : livros;
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao buscar todos os Livros.", e);
        }
    }

    @Override
    public Livro buscarPorId(Long id) {
        try {
            return livroRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Livro não encontrado para o ID: " + id));
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao buscar Livro por ID.", e);
        }
    }

    public void apagarPorId(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new RuntimeException("Livro não encontrado para o ID: " + id);
        }
        try {
            livroRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Não é possível excluir o Livro, pois ele está referenciado em outra tabela.", e);
        }
    }

}
