package com.spassu.tj.biblioteca.service;

import com.spassu.tj.biblioteca.model.Livro;

import java.util.List;

public interface LivroService {

    void criar(Livro livro);

    List<Livro> buscarTodos();

    Livro buscarPorId(Long id);

    void apagarPorId(Long id);
}
