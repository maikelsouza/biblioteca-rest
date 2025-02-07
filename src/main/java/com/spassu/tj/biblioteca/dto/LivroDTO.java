package com.spassu.tj.biblioteca.dto;

import com.spassu.tj.biblioteca.model.Livro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LivroDTO {

    private Long codL;
    private String titulo;
    private String editora;
    private Integer edicao;
    private String anoPublicacao;
    private BigDecimal valor;
    private List<AutorDTO> autores;
    private List<AssuntoDTO> assuntos;

    public static List<LivroDTO> convertToDTO(List<Livro> livros) {
        return livros.stream().map(LivroDTO::convertToDTO).collect(Collectors.toList());
    }

    public static LivroDTO convertToDTO(Livro livro) {
        return LivroDTO.builder()
                .codL(livro.getCodL())
                .titulo(livro.getTitulo())
                .editora(livro.getEditora())
                .edicao(livro.getEdicao())
                .valor(livro.getValor())
                .anoPublicacao(livro.getAnoPublicacao())
                .autores(AutorDTO.convertToDTO(livro.getAutores()))
                .assuntos((AssuntoDTO.convertToDTO(livro.getAssuntos())))
                .build();
    }

    public static Livro convertToEntity(LivroDTO livroDTO) {
        return Livro.builder()
                .codL(livroDTO.getCodL())
                .titulo(livroDTO.getTitulo())
                .editora(livroDTO.getEditora())
                .edicao(livroDTO.getEdicao())
                .valor(livroDTO.getValor())
                .anoPublicacao((livroDTO.getAnoPublicacao()))
                .autores(AutorDTO.convertToEntity(livroDTO.getAutores()))
                .assuntos(AssuntoDTO.convertToEntity(livroDTO.getAssuntos()))
                .build();
    }


}
