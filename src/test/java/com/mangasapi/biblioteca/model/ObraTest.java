package com.mangasapi.biblioteca.model;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ObraTest {
    private final Long idValido = 1L;
    private final String tituloValido = "Título Teste";
    private final Autor autorValido = new Autor(100L, "Autor Teste"); // Usando Autor objeto
    private final String generoValido = "Gênero Teste";
    private final String caminhoValido = "/caminho/teste.pdf";
    private final int ANO_ATUAL = Year.now().getValue();

    @Test
    void testCriacaoObra_AnoMenorQueLimite_AVL_999() {
        int anoInvalido = 999;
        assertThrows(IllegalArgumentException.class, () -> new Obra(idValido, tituloValido, autorValido, generoValido, anoInvalido, caminhoValido),
                "PE Inválida (<1000): Deve falhar com o ano 999.");
    }

    @Test
    void testCriacaoObra_AnoNoLimiteMinimo_AVL_1000() {
        int anoValido = 1000;
        assertDoesNotThrow(() -> new Obra(idValido, tituloValido, autorValido, generoValido, anoValido, caminhoValido),
                "PE Válida ([1000, 2025]): Deve ser aceito com o ano 1000 (Limite Mínimo).");
    }

    @Test
    void testCriacaoObra_AnoAcimaDoMinimo_AVL_1001() {
        int anoValido = 1001;
        assertDoesNotThrow(() -> new Obra(idValido, tituloValido, autorValido, generoValido, anoValido, caminhoValido),
                "PE Válida ([1000, 2025]): Deve ser aceito com o ano 1001.");
    }

    // AVL: Valores de Limite Superior (Ano Atual - 1, Ano Atual, Ano Atual + 1)
    @Test
    void testCriacaoObra_AnoAbaixoDoMaximo_AVL_AnoAtualMenos1() {
        int anoValido = ANO_ATUAL - 1;
        assertDoesNotThrow(() -> new Obra(idValido, tituloValido, autorValido, generoValido, anoValido, caminhoValido),
                "PE Válida ([1000, 2025]): Deve ser aceito com o ano " + anoValido + ".");
    }

    @Test
    void testCriacaoObra_AnoNoLimiteMaximo_AVL_AnoAtual() {
        int anoValido = ANO_ATUAL;
        assertDoesNotThrow(() -> new Obra(idValido, tituloValido, autorValido, generoValido, anoValido, caminhoValido),
                "PE Válida ([1000, 2025]): Deve ser aceito com o ano " + anoValido + " (Limite Máximo).");
    }

    @Test
    void testCriacaoObra_AnoAcimaDoLimite_AVL_AnoAtualMais1() {
        int anoInvalido = ANO_ATUAL + 1;
        assertThrows(IllegalArgumentException.class, () -> new Obra(idValido, tituloValido, autorValido, generoValido, anoInvalido, caminhoValido),
                "PE Inválida (>2025): Deve falhar com o ano " + anoInvalido + ".");
    }


}
