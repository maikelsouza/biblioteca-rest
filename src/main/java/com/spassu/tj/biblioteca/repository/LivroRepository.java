package com.spassu.tj.biblioteca.repository;

import com.spassu.tj.biblioteca.model.Livro;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LivroRepository extends CrudRepository<Livro, Long> {

    List<Livro> findAll(Sort sort);
}
