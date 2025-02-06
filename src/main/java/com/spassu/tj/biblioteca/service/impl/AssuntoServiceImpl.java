package com.spassu.tj.biblioteca.service.impl;

import com.spassu.tj.biblioteca.model.Assunto;
import com.spassu.tj.biblioteca.repository.AssuntoRepository;
import com.spassu.tj.biblioteca.service.AssuntoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AssuntoServiceImpl implements AssuntoService {

    @Autowired
    private AssuntoRepository assuntoRepository;

    @Override
    public void criar(Assunto assunto) {
        assuntoRepository.save(assunto);
    }

    @Override
    public List<Assunto> buscarTodos() {
        return (List<Assunto>) assuntoRepository.findAll();
    }

    @Override
    public Assunto buscarPorId(Long id) {
        return assuntoRepository.findById(id).get();
    }
}
