package com.spassu.tj.biblioteca.service.impl;

import com.spassu.tj.biblioteca.model.Livro;
import com.spassu.tj.biblioteca.repository.LivroRepository;
import com.spassu.tj.biblioteca.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LivroServiceImpl implements LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public void criar(Livro livro) {
        livroRepository.save(livro);
    }

    @Override
    public List<Livro> buscarTodos() {
        return (List<Livro>) livroRepository.findAll();
    }

    @Override
    public Livro buscarPorId(Long id) {
        return livroRepository.findById(id).get();
    }
}
