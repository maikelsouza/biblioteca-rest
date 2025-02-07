package com.spassu.tj.biblioteca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table()
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Assunto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codAs;

    @Column(length = 20, nullable = false)
    @NotBlank(message = "A descrição não pode estar em branco.")
    @Size(max = 20, message = "A descrição deve ter no máximo 20 caracteres.")
    private String descricao;

    @ManyToMany(mappedBy = "assuntos")
    private Set<Livro> livros;

}
