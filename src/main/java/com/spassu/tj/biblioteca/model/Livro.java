package com.spassu.tj.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table()
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codL;

    @NotBlank(message = "O título não pode estar em branco.")
    @Size(max = 40, message = "O título deve ter no máximo 40 caracteres.")
    @Column(length = 40, nullable = false)
    private String titulo;

    @NotBlank(message = "A editora não pode estar em branco.")
    @Size(max = 40, message = "A editora deve ter no máximo 40 caracteres.")
    @Column(length = 40)
    private String editora;

    private Integer edicao;

    @Size(max = 4, message = "O ano de publicação deve ter no máximo 4 caracteres.")
    @Column(length = 4)
    private String anoPublicacao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @ManyToMany()
    @JoinTable(
            name = "Livro_Autor",
            joinColumns = @JoinColumn(name = "Livro_CodL"),
            inverseJoinColumns = @JoinColumn(name = "Autor_CodAu")
    )
    @JsonManagedReference
    private List<Autor> autores;

    @ManyToMany
    @JoinTable(
            name = "Livro_Assunto",
            joinColumns = @JoinColumn(name = "Livro_CodL"),
            inverseJoinColumns = @JoinColumn(name = "Assunto_CodAs")
    )
    private Set<Assunto> assuntos;
}
