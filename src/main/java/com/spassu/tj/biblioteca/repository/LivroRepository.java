package com.spassu.tj.biblioteca.repository;

import com.spassu.tj.biblioteca.model.Livro;
import org.springframework.data.repository.CrudRepository;

public interface LivroRepository extends CrudRepository<Livro, Long> {

}
