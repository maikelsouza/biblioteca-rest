package com.spassu.tj.biblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table()
@Data
@NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codL;

    @Column(length = 40, nullable = false)
    private String titulo;

    @Column(length = 40)
    private String editora;

    private Integer edicao;

    @Column(length = 4)
    private String anoPublicacao;

//    @Column(nullable = false, precision = 10, scale = 2)
//    private BigDecimal valor;

    @ManyToMany
    @JoinTable(
            name = "Livro_Autor",
            joinColumns = @JoinColumn(name = "Livro_CodL"),
            inverseJoinColumns = @JoinColumn(name = "Autor_CodAu")
    )
    private Set<Autor> autores;

    @ManyToMany
    @JoinTable(
            name = "Livro_Assunto",
            joinColumns = @JoinColumn(name = "Livro_CodL"),
            inverseJoinColumns = @JoinColumn(name = "Assunto_CodAs")
    )
    private Set<Assunto> assuntos;
}
