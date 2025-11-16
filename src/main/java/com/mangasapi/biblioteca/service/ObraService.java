package com.mangasapi.biblioteca.service;

import com.mangasapi.biblioteca.model.Autor;
import com.mangasapi.biblioteca.model.Obra;

import java.time.Year;

public class ObraService {
    public Obra criarObra(Long id, String titulo, Autor autor, String genero, int anoPublicacao, String caminhoArquivo) {
        // Validação de ano de publicação: PE (Válida: 1000 até o ano atual), AVL nas bordas.
        int anoAtual = Year.now().getValue();
        if (anoPublicacao < 1000 || anoPublicacao > anoAtual) {
            throw new IllegalArgumentException("Ano de publicação inválido. Deve ser entre 1000 e " + anoAtual + ".");
        }

        return new Obra(id, titulo, autor, genero, anoPublicacao, caminhoArquivo);
    }
}
