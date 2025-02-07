package com.spassu.tj.biblioteca.service;

import com.spassu.tj.biblioteca.dto.AssuntoDTO;
import com.spassu.tj.biblioteca.model.Assunto;

import java.util.List;

public interface AssuntoService {

    Assunto criar(Assunto assunto);

    Assunto atualizar(Long id, AssuntoDTO assuntoDTO);

    List<Assunto> buscarTodos();

    Assunto buscarPorId(Long id);

    void apagarPorId(Long id);
}
