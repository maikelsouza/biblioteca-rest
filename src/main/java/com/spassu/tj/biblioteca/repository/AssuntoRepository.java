package com.spassu.tj.biblioteca.repository;

import com.spassu.tj.biblioteca.model.Assunto;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AssuntoRepository extends CrudRepository<Assunto, Long> {

    List<Assunto> findAll(Sort sort);
}
