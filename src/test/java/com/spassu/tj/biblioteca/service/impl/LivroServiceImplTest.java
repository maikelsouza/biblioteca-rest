package com.spassu.tj.biblioteca.service.impl;

import com.spassu.tj.biblioteca.dto.AssuntoDTO;
import com.spassu.tj.biblioteca.dto.AutorDTO;
import com.spassu.tj.biblioteca.dto.LivroDTO;
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
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    void testAtualizar() {
        // arrange
        Livro livro = contruirLivro();
        LivroDTO livroDTO = contruirLivroDTO();
        when(livroRepository.findById(anyLong())).thenReturn(Optional.of(livro));
        when(livroRepository.save(any(Livro.class))).thenReturn(livro);

        // act
        Livro livroAtualizado = livroService.atualizar(1L, livroDTO);

        // assert
        assertNotNull(livroAtualizado);
    }

    @Test
    void testBuscarTodos() {
        // arrange
        Livro livro = contruirLivro();
        when(livroRepository.findAll()).thenReturn(Collections.singletonList(livro));

        // act
        List<Livro> livros = livroService.buscarTodos();

        // assert
        assertFalse(livros.isEmpty());
        assertEquals(1, livros.size());
    }

    @Test
    void testBuscarPorId() {
        // arrange
        Livro livro = contruirLivro();
        when(livroRepository.findById(anyLong())).thenReturn(Optional.of(livro));

        // act
        Livro livroRetorno = livroService.buscarPorId(1L);

        // assert
        assertNotNull(livroRetorno);
        assertEquals(1L, livroRetorno.getCodL());
    }

    @Test
    void testApagarPorId() {
        // arrange
        when(livroRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(livroRepository).deleteById(anyLong());

        // assert
        livroService.apagarPorId(1L);

        // assert
        verify(livroRepository, times(1)).deleteById(1L);
    }



    private LivroDTO contruirLivroDTO(){
        return LivroDTO.builder()
                .codL(1L)
                .titulo("O Guarani")
                .valor(BigDecimal.ONE)
                .editora("Principis")
                .edicao(1)
                .assuntos(construirListaAssuntosDTO())
                .autores(construirListaAutoresDTO())
                .build();
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

    private List<AssuntoDTO> construirListaAssuntosDTO(){
        return List.of(construirAssuntoDTO(),construirAssuntoDTO());
    }

    private AssuntoDTO construirAssuntoDTO(){
        return AssuntoDTO.builder()
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

    private List<AutorDTO> construirListaAutoresDTO(){
        return List.of(construirAutorDTO(),construirAutorDTO());
    }

    private AutorDTO construirAutorDTO(){
        return AutorDTO.builder()
                .codAu(gerarRandomUmAteCem())
                .nome("José de Alencar")
                .build();
    }

    private Long gerarRandomUmAteCem(){
        Random random = new Random();
        return random.nextLong(100) + 1;
    }

}