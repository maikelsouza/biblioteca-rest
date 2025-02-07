package com.spassu.tj.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table()
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Autor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codAu;

    @NotBlank(message = "O nome não pode estar em branco.")
    @Size(max = 40, message = "O nome deve ter no máximo 40 caracteres.")
    @Column(length = 40, nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "autores")
    @JsonBackReference
    private List<Livro> livros;
}

