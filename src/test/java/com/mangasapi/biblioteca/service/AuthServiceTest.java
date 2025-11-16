package com.mangasapi.biblioteca.service;

import com.mangasapi.biblioteca.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceTest {
    private AuthService authService;
    private UsuarioService usuarioService = new UsuarioService(); // Para criar o usuário inicial validado
    private final String emailExistente = "admin@acervo.com";
    private final String senhaCorreta = "SenhaSegura123";

    @BeforeEach
    void setUp() {
        Usuario admin = usuarioService.criarUsuario(1L, "Admin", emailExistente, senhaCorreta);
        // Inicializa o serviço com um usuário para poder testar o login
        authService = new AuthService(List.of(admin));
    }

    // Testes de Partição de Equivalência (PE) - Login Válido
    @Test
    void testLogin_CredenciaisCorretas_PE_Valida() {
        // PE: Credenciais corretas (usuário existe, senha correta)
        Usuario logado = assertDoesNotThrow(() -> authService.login(emailExistente, senhaCorreta));
        assertNotNull(logado);
        assertEquals(emailExistente, logado.getEmail());
    }

    // Testes de Partição de Equivalência (PE) - Login Inválido
    @Test
    void testLogin_EmailInexistente_PE_Invalida() {
        // PE: Usuário inexistente
        assertThrows(IllegalArgumentException.class, () -> authService.login("naoexiste@email.com", senhaCorreta),
                "PE Inválida: Deve falhar se o email não estiver cadastrado.");
    }

    @Test
    void testLogin_SenhaIncorreta_PE_Invalida() {
        // PE: Senha incorreta
        assertThrows(IllegalArgumentException.class, () -> authService.login(emailExistente, "SenhaErrada456"),
                "PE Inválida: Deve falhar se a senha estiver incorreta.");
    }

    @Test
    void testLogin_EntradasVaziasOuNulas_PE_Invalida() {
        // PE: Entrada nula
        assertThrows(IllegalArgumentException.class, () -> authService.login(null, senhaCorreta),
                "PE Inválida: Deve falhar com email nulo.");
        // PE: Entrada vazia
        assertThrows(IllegalArgumentException.class, () -> authService.login(emailExistente, ""),
                "PE Inválida: Deve falhar com senha vazia.");
        // PE: Ambas nulas
        assertThrows(IllegalArgumentException.class, () -> authService.login(null, null),
                "PE Inválida: Deve falhar com ambas as credenciais nulas.");
    }
}
