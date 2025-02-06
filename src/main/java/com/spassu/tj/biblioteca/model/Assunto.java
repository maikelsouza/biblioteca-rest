package com.spassu.tj.biblioteca.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table()
@Data
@NoArgsConstructor
public class Assunto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codAs;

    @Column(length = 20, nullable = false)
    private String descricao;

    @ManyToMany(mappedBy = "assuntos")
    private Set<Livro> livros;

}
