package com.mangasapi.biblioteca.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AutorTest {

    private final Long idValido = 1L;


    @Test
    void testCriacaoAutor_NomeNoLimiteMinimo_AVL_3() {
        String nomeValido = "abc"; // 3 chars
        assertDoesNotThrow(() -> new Autor(idValido, nomeValido),
                "PE Válida (>=3): Deve ser aceito com 3 caracteres (Limite Mínimo).");
    }

    @Test
    void testCriacaoAutor_NomeAcimaDoMinimo_AVL_4() {
        String nomeValido = "abcd"; // 4 chars
        assertDoesNotThrow(() -> new Autor(idValido, nomeValido),
                "PE Válida (>=3): Deve ser aceito com 4 caracteres.");
    }


}
