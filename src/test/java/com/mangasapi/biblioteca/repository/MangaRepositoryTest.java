package com.mangasapi.biblioteca.repository;

import com.mangasapi.biblioteca.model.Autor;
import com.mangasapi.biblioteca.model.Obra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MangaRepositoryTest {
    private MangaRepository mangaRepository;
    private Autor stephenKing;
    private Autor robertC;
    private Autor sunTzu;

    @BeforeEach
    void setUp() {
        mangaRepository = new MangaRepository();

        // Criando Autores adicionais para os testes
        long nextAutorId = mangaRepository.nextAutorId;
        stephenKing = new Autor(nextAutorId++, "Stephen King");
        robertC = new Autor(nextAutorId++, "Robert C.");
        sunTzu = new Autor(nextAutorId++, "Sun Tzu");

        mangaRepository.adicionarObra("O Iluminado", stephenKing, "Terror", 1977, "/files/iluminado.pdf");
        mangaRepository.adicionarObra("O Chamado", robertC, "Terror", 2005, "/files/chamado.pdf");
        mangaRepository.adicionarObra("A Arte da Guerra", sunTzu, "Estrategia", 1000, "/files/guerra.pdf");
    }

    @Test
    void testBuscarEFiltrar_TermoMenorQueLimite_AVL_2() {
        // PE Inválida: Termo de busca com menos de 3 caracteres
        String termoInvalido = "il"; // 2 chars
        assertThrows(IllegalArgumentException.class, () -> mangaRepository.buscarEFiltrar(termoInvalido, null),
                "PE Inválida/AVL 2: Deve falhar com 2 caracteres.");
    }

    @Test
    void testBuscarEFiltrar_TermoNoLimiteMinimo_AVL_3() {
        // PE Válida: Termo de busca com exatamente 3 caracteres
        String termoValido = "Dom"; // 3 chars (de "Dom Casmurro")
        List<Obra> resultados = assertDoesNotThrow(() -> mangaRepository.buscarEFiltrar(termoValido, null),
                "PE Válida/AVL 3: Deve ser aceito com 3 caracteres.");
        assertFalse(resultados.isEmpty());
        assertEquals(1, resultados.size()); // Dom Casmurro
    }

    // Testes de Partição de Equivalência (PE) - Filtragem e Busca por Autor

    @Test
    void testBuscarEFiltrar_BuscaPorNomeDeAutor_PE_Valida() {
        // PE: Busca por termo que corresponde ao nome de um autor (Stephen King)
        List<Obra> resultados = mangaRepository.buscarEFiltrar("Stephen", null);
        assertFalse(resultados.isEmpty());
        assertEquals(1, resultados.size()); // O Iluminado
    }

    @Test
    void testBuscarEFiltrar_BuscaComTermoEFiltragem_PE_Valida() {
        // PE: Busca por termo (King) E filtro (Terror)
        List<Obra> resultados = mangaRepository.buscarEFiltrar("King", "Terror");
        assertFalse(resultados.isEmpty());
        assertEquals(1, resultados.size()); // O Iluminado

        // PE: Busca por termo (Dom) E filtro (Terror) - Espera-se vazio
        List<Obra> resultadosVazios = mangaRepository.buscarEFiltrar("Dom", "Terror");
        assertTrue(resultadosVazios.isEmpty());
    }

    @Test
    void testBuscarEFiltrar_BuscaSemFiltro_PE_Valida() {
        // PE: Apenas termo de busca
        List<Obra> resultados = mangaRepository.buscarEFiltrar("Homero", null);
        assertFalse(resultados.isEmpty());
        assertEquals(1, resultados.size()); // A Odisséia
    }

}
