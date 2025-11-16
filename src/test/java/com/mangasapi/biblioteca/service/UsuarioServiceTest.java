package com.mangasapi.biblioteca.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsuarioServiceTest {
    private UsuarioService usuarioService;
    private final Long idValido = 1L;
    private final String nomeValido = "Nome Teste";
    private final String emailValido = "teste@exemplo.com";

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService();
    }

    // AVL: Valores de Limite Inferior (7, 8, 9)
    @Test
    void testCriacaoUsuario_SenhaMenorQueLimite_AVL_7() {
        String senhaInvalida = "1234567"; // 7 chars
        assertThrows(IllegalArgumentException.class,
                () -> usuarioService.criarUsuario(idValido, nomeValido, emailValido, senhaInvalida),
                "PE Inválida (<8): Deve falhar com 7 caracteres.");
    }

    @Test
    void testCriacaoUsuario_SenhaNoLimiteMinimo_AVL_8() {
        String senhaValida = "12345678"; // 8 chars
        assertDoesNotThrow(() -> usuarioService.criarUsuario(idValido, nomeValido, emailValido, senhaValida),
                "PE Válida ([8,30]): Deve ser aceito com 8 caracteres (Limite Mínimo).");
    }

    // AVL: Valores de Limite Superior (29, 30, 31)
    @Test
    void testCriacaoUsuario_SenhaNoLimiteMaximo_AVL_30() {
        String senhaValida = "a".repeat(30); // 30 chars
        assertDoesNotThrow(() -> usuarioService.criarUsuario(idValido, nomeValido, emailValido, senhaValida),
                "PE Válida ([8,30]): Deve ser aceito com 30 caracteres (Limite Máximo).");
    }

    @Test
    void testCriacaoUsuario_SenhaAcimaDoLimite_AVL_31() {
        String senhaInvalida = "a".repeat(31); // 31 chars
        assertThrows(IllegalArgumentException.class,
                () -> usuarioService.criarUsuario(idValido, nomeValido, emailValido, senhaInvalida),
                "PE Inválida (>30): Deve falhar com 31 caracteres.");
    }

    @Test
    void testCriacaoUsuario_EmailValido_PE_Valida() {
        String emailCorreto = "teste@dominio.com";
        assertDoesNotThrow(() -> usuarioService.criarUsuario(idValido, nomeValido, emailCorreto, "SenhaForte1"),
                "PE Válida: Deve aceitar email com '@'.");
    }

    @Test
    void testCriacaoUsuario_EmailInvalidoSemArroba_PE_Invalida() {
        String emailIncorreto = "testedominiocom";
        assertThrows(IllegalArgumentException.class,
                () -> usuarioService.criarUsuario(idValido, nomeValido, emailIncorreto, "SenhaForte1"),
                "PE Inválida: Deve falhar se não contiver '@'.");
    }

    @Test
    void testCriacaoUsuario_EmailNulo_PE_Invalida() {
        assertThrows(IllegalArgumentException.class,
                () -> usuarioService.criarUsuario(idValido, nomeValido, null, "SenhaForte1"),
                "PE Inválida: Deve falhar com email nulo.");
    }

}
