package com.mangasapi.biblioteca.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    private final Long idValido = 1L;
    private final String nomeValido = "Nome Teste";
    private final String emailValido = "teste@exemplo.com";
    private final String senhaValida = "SenhaSegura123";

    @Test
    void testCriacaoUsuario_ConstrutorVazio() {
        Usuario user = new Usuario();
        assertNotNull(user);
    }

    @Test
    void testCriacaoUsuario_ConstrutorCompleto() {
        // Garante que o construtor limpo não lança exceção, mesmo com dados "inválidos",
        // pois a validação foi delegada à Service.
        Usuario user = assertDoesNotThrow(() -> new Usuario(idValido, nomeValido, emailValido, senhaValida),
                "O construtor limpo não deve lançar exceção.");
        assertEquals(emailValido, user.getEmail());
    }

}
