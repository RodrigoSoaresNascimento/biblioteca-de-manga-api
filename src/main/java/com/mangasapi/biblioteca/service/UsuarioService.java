package com.mangasapi.biblioteca.service;

import com.mangasapi.biblioteca.model.Usuario;

public class UsuarioService {
    public Usuario criarUsuario(Long id, String nome, String email, String senha) {
        // Regra de Validação PE/AVL: Senha
        if (senha == null || senha.length() < 8 || senha.length() > 30) {
            throw new IllegalArgumentException("A senha deve ter entre 8 e 30 caracteres.");
        }
        // Regra de Validação: Email
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }

        return new Usuario(id, nome, email, senha);
    }
}
