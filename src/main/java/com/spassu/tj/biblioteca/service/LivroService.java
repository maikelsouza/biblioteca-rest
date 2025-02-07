package com.spassu.tj.biblioteca.service;

import com.spassu.tj.biblioteca.dto.LivroDTO;
import com.spassu.tj.biblioteca.model.Livro;

import java.util.List;

public interface LivroService {

    Livro criar(Livro livro);

    Livro atualizar(Long id, LivroDTO livroDTO);

    List<Livro> buscarTodos();

    Livro buscarPorId(Long id);

    void apagarPorId(Long id);
}
