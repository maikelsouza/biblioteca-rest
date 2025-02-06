package com.spassu.tj.biblioteca.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table()
@Data
@NoArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codAu;

    @Column(length = 40, nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "autores")
    private Set<Livro> livros;


}

