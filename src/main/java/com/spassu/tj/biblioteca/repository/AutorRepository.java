package com.spassu.tj.biblioteca.repository;

import com.spassu.tj.biblioteca.model.Autor;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AutorRepository extends CrudRepository<Autor, Long> {

    List<Autor> findAll(Sort sort);
}
