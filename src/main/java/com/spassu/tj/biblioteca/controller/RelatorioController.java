package com.spassu.tj.biblioteca.controller;

import com.spassu.tj.biblioteca.service.RelatorioAutorService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/relatorios")
public class RelatorioController {


    private static final Logger logger = LogManager.getLogger(RelatorioController.class);

    @Autowired
    private final RelatorioAutorService relatorioAutorService;


    @GetMapping("/autores-pdf")
    public ResponseEntity<byte[]> gerarAutoresPdf() {
        logger.info("Requisição para gerar relatório em PDF usando JasperReports.");
        try {

            ByteArrayOutputStream out = relatorioAutorService.gerarRelatorioAutores();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=relatorio_autores.pdf");

            return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao gerar relatório em PDF com JasperReports: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
