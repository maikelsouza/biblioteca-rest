package com.spassu.tj.biblioteca.dto;

import com.spassu.tj.biblioteca.model.Autor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AutorDTO {

    private Long codAu;

    private String nome;


    public static List<AutorDTO> convertToDTO(List<Autor> autores) {
        return autores.stream().map(AutorDTO::convertToDTO).collect(Collectors.toList());
    }

    public static AutorDTO convertToDTO(Autor autor) {
        return AutorDTO.builder()
                .codAu(autor.getCodAu())
                .nome(autor.getNome())
                .build();
    }

    public static Autor convertToEntity(AutorDTO autorDTO) {
        return Autor.builder()
                .codAu(autorDTO.getCodAu())
                .nome(autorDTO.getNome())
                .build();
    }

}
