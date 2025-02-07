package com.spassu.tj.biblioteca.service.impl;

import com.spassu.tj.biblioteca.service.RelatorioAutorService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class RelatorioAutorServiceImpl implements RelatorioAutorService {


    @Autowired
    private DataSource dataSource;

    @Override
    public ByteArrayOutputStream gerarRelatorioAutores() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Connection conexao = null;
        InputStream jasperStream = null;

        try {
            conexao = dataSource.getConnection();
            jasperStream = getClass().getResourceAsStream("/relatorios/relatorio-autor.jasper");

            if (jasperStream == null) {
                throw new RuntimeException("Arquivo do relatório não encontrado.");
            }

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, new HashMap<>(), conexao);
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);

            return out;
        } catch (JRException e) {
            throw new RuntimeException("Erro ao gerar relatório: " + e.getMessage(), e);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter conexão com o banco de dados: " + e.getMessage(), e);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
                if (jasperStream != null) {
                    jasperStream.close();
                }
            } catch (IOException | SQLException e) {
                throw new RuntimeException("Erro ao fechar recursos: " + e.getMessage(), e);
            }
        }
    }



}
