package com.spassu.tj.biblioteca.service;

import com.spassu.tj.biblioteca.model.Autor;
import com.spassu.tj.biblioteca.model.Livro;

import java.util.List;

public interface AutorService {

    void criar(Autor autor);

    List<Autor> buscarTodos();

    Autor buscarPorId(Long id);
}
