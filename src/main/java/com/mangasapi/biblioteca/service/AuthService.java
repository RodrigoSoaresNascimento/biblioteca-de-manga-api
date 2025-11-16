package com.mangasapi.biblioteca.service;

import com.mangasapi.biblioteca.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthService {

    private List<Usuario> usuarios = new ArrayList<>();
    private Long nextId = 1L;

    // Construtor usado apenas para fins de teste simples (simular um usuário pré-existente)
    public AuthService(List<Usuario> initialUsers) {
        if (initialUsers != null) {
            this.usuarios.addAll(initialUsers);
            this.nextId = (long) (initialUsers.size() + 1);
        }
    }

    public Usuario login(String email, String senha) {
        // Partição de Equivalência: Entrada nula/vazia
        if (email == null || email.trim().isEmpty() || senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("Email e senha não podem ser vazios.");
        }

        Optional<Usuario> userOptional = usuarios.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();

        // Partição de Equivalência: Usuário não encontrado
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Credenciais inválidas: Usuário não encontrado.");
        }

        Usuario usuario = userOptional.get();

        // Partição de Equivalência: Senha incorreta
        if (!usuario.validarSenha(senha)) {
            throw new IllegalArgumentException("Credenciais inválidas: Senha incorreta.");
        }

        return usuario;
    }

    public Usuario cadastrar(String nome, String email, String senha) {
        if (usuarios.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email))) {
            throw new IllegalStateException("Email já cadastrado.");
        }
        Usuario novoUsuario = new Usuario(nextId++, nome, email, senha);
        usuarios.add(novoUsuario);
        return novoUsuario;
    }

}
