package com.spassu.tj.biblioteca.service;

import com.spassu.tj.biblioteca.model.Assunto;
import com.spassu.tj.biblioteca.model.Autor;

import java.util.List;

public interface AssuntoService {

    void criar(Assunto assunto);

    List<Assunto> buscarTodos();

    Assunto buscarPorId(Long id);
}
