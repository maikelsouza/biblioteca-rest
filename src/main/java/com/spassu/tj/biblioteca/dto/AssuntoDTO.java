package com.spassu.tj.biblioteca.dto;

import com.spassu.tj.biblioteca.model.Assunto;
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
public class AssuntoDTO {

    private Long codAs;

    private String descricao;


    public static List<AssuntoDTO> convertToDTO(List<Assunto> assuntos) {
        return assuntos.stream().map(AssuntoDTO::convertToDTO).collect(Collectors.toList());
    }

    public static AssuntoDTO convertToDTO(Assunto assunto) {
        return AssuntoDTO.builder()
                .codAs(assunto.getCodAs())
                .descricao(assunto.getDescricao())
                .build();
    }

    public static List<Assunto> convertToEntity(List<AssuntoDTO> assuntoDTOS) {
        return assuntoDTOS.stream().map(AssuntoDTO::convertToEntity).collect(Collectors.toList());
    }

    public static Assunto convertToEntity(AssuntoDTO assuntoDTO) {
        return Assunto.builder()
                .codAs(assuntoDTO.getCodAs())
                .descricao(assuntoDTO.getDescricao())
                .build();
    }

}
