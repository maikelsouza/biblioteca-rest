package com.spassu.tj.biblioteca.service;

import com.spassu.tj.biblioteca.dto.AutorDTO;
import com.spassu.tj.biblioteca.model.Autor;

import java.util.List;

public interface AutorService {

    Autor criar(Autor autor);

    Autor atualizar(Long id, AutorDTO autor);

    List<Autor> buscarTodos();

    Autor buscarPorId(Long id);

    void apagarPorId(Long id);
}
