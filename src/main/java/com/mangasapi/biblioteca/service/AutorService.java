package com.mangasapi.biblioteca.service;

import com.mangasapi.biblioteca.model.Autor;

public class AutorService {

    public Autor criarAutor(Long id, String nome) {
        // Regra de Validação PE/AVL: Nome deve ter pelo menos 3 caracteres.
        if (nome == null || nome.trim().length() < 3) {
            throw new IllegalArgumentException("O nome do autor deve ter pelo menos 3 caracteres e não pode ser vazio.");
        }
        return new Autor(id, nome);
    }

}
