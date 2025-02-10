package com.spassu.tj.biblioteca.service.impl;

import com.spassu.tj.biblioteca.model.Assunto;
import com.spassu.tj.biblioteca.model.Autor;
import com.spassu.tj.biblioteca.model.Livro;
import com.spassu.tj.biblioteca.repository.LivroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
class LivroServiceImplTest {

    @Mock
    private LivroRepository livroRepository;

    @InjectMocks
    private LivroServiceImpl livroService;


    @Test
    void testCriar() {
        // arrange
        Livro livro = contruirLivro();
        when(livroRepository.save(any(Livro.class))).thenReturn(livro);

        // act
        Livro livroCriado = livroService.criar(livro);

        // assert
        assertNotNull(livroCriado);
        assertEquals(1L, livroCriado.getCodL());
        assertEquals("O Guarani", livroCriado.getTitulo());
        assertEquals(2, livroCriado.getAutores().size());
        assertEquals(2, livroCriado.getAssuntos().size());
    }


    private Livro contruirLivro(){
        return Livro.builder()
                .codL(1L)
                .titulo("O Guarani")
                .valor(BigDecimal.ONE)
                .editora("Principis")
                .edicao(1)
                .assuntos(construirListaAssuntos())
                .autores(construirListaAutores())
                .build();
    }

    private List<Assunto> construirListaAssuntos(){
        return List.of(construirAssunto(),construirAssunto());
    }

    private Assunto construirAssunto(){
        return Assunto.builder()
                .codAs(gerarRandomUmAteCem())
                .descricao("Romance")
                .build();
    }

    private List<Autor> construirListaAutores(){
        return List.of(construirAutor(),construirAutor());
    }

    private Autor construirAutor(){
        return Autor.builder()
                .codAu(gerarRandomUmAteCem())
                .nome("José de Alencar")
                .build();
    }

    private Long gerarRandomUmAteCem(){
        Random random = new Random();
        return random.nextLong(100) + 1;
    }

}