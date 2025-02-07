CREATE VIEW vw_autores_livros_assuntos AS
SELECT
   a.NOME,
   l.TITULO,
   l.ANO_PUBLICACAO,
   l.VALOR,
   ass.DESCRICAO
FROM AUTOR a
JOIN LIVRO_AUTOR  la ON a.COD_AU = la.AUTOR_COD_AU
JOIN LIVRO l ON la.LIVRO_CODL  =  l.CODL
JOIN LIVRO_ASSUNTO las ON l.CODL = las.LIVRO_CODL
JOIN ASSUNTO ass ON las.ASSUNTO_COD_AS = ass.COD_AS
order by a.NOME, l.TITULO