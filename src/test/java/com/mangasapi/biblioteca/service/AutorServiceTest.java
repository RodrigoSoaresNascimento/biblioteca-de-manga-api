package com.mangasapi.biblioteca.service;

import com.mangasapi.biblioteca.model.Autor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AutorServiceTest {
    private AutorService autorService;
    private final Long idValido = 1L;

    @BeforeEach
    void setUp() {
        autorService = new AutorService();
    }

    // AVL: Valores de Limite Inferior (2, 3, 4)
    @Test
    void testCriacaoAutor_NomeMenorQueLimite_AVL_2() {
        String nomeInvalido = "ab"; // 2 chars
        assertThrows(IllegalArgumentException.class, () -> autorService.criarAutor(idValido, nomeInvalido),
                "PE Inválida (<3): Deve falhar com 2 caracteres (AVL).");
    }

    @Test
    void testCriacaoAutor_NomeNoLimiteMinimo_AVL_3() {
        String nomeValido = "abc"; // 3 chars
        Autor autor = assertDoesNotThrow(() -> autorService.criarAutor(idValido, nomeValido),
                "PE Válida (>=3): Deve ser aceito com 3 caracteres (Limite Mínimo).");
        assertEquals("abc", autor.getNome());
    }

    @Test
    void testCriacaoAutor_NomeAcimaDoMinimo_AVL_4() {
        String nomeValido = "abcd"; // 4 chars
        assertDoesNotThrow(() -> autorService.criarAutor(idValido, nomeValido),
                "PE Válida (>=3): Deve ser aceito com 4 caracteres.");
    }

    @Test
    void testCriacaoAutor_NomeNuloOuVazio_PE_Invalida() {
        // PE Inválida: nulo
        assertThrows(IllegalArgumentException.class, () -> autorService.criarAutor(idValido, null),
                "PE Inválida: Deve falhar com nome nulo.");
        // PE Inválida: vazio (apenas espaços)
        assertThrows(IllegalArgumentException.class, () -> autorService.criarAutor(idValido, "  "),
                "PE Inválida: Deve falhar com nome vazio/apenas espaços.");
    }
}
