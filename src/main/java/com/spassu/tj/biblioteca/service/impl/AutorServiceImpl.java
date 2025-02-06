package com.spassu.tj.biblioteca.service.impl;

import com.spassu.tj.biblioteca.model.Autor;
import com.spassu.tj.biblioteca.repository.AutorRepository;
import com.spassu.tj.biblioteca.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public void criar(Autor autor) {
        autorRepository.save(autor);
    }

    @Override
    public List<Autor> buscarTodos() {
        return (List<Autor>) autorRepository.findAll();
    }

    @Override
    public Autor buscarPorId(Long id) {
        return autorRepository.findById(id).get();
    }
}
