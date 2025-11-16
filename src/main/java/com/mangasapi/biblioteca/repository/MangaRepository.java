package com.mangasapi.biblioteca.repository;

import com.mangasapi.biblioteca.model.Autor;
import com.mangasapi.biblioteca.model.Obra;

import java.util.ArrayList;
import java.util.List;

public class MangaRepository {

    private List<Obra> obras = new ArrayList<>();
    private Long nextId = 1L;
    public Long nextAutorId = 1L; // Novo ID para Autores

    public MangaRepository() {
        // Criação de Autores para inicialização
        Autor homero = new Autor(nextAutorId++, "Homero");
        Autor orwell = new Autor(nextAutorId++, "George Orwell");
        Autor machado = new Autor(nextAutorId++, "Machado de Assis");

        // Inicializa com algumas obras para teste
        obras.add(new Obra(nextId++, "A Odisséia", homero, "Épico", 1888, "/files/odisseia.pdf"));
        obras.add(new Obra(nextId++, "1984", orwell, "Ficção Científica", 1949, "/files/1984.pdf"));
        obras.add(new Obra(nextId++, "Dom Casmurro", machado, "Romance", 1899, "/files/domcasmurro.pdf"));
    }

    public Obra adicionarObra(String titulo, Autor autor, String genero, int anoPublicacao, String caminhoArquivo) {
        Obra novaObra = new Obra(nextId++, titulo, autor, genero, anoPublicacao, caminhoArquivo);
        obras.add(novaObra);
        return novaObra;
    }

    public List<Obra> buscarEFiltrar(String termo, String genero) {
        String termoLowerCase = (termo != null) ? termo.toLowerCase().trim() : "";
        String generoLowerCase = (genero != null) ? genero.toLowerCase().trim() : "";

        // Partição de Equivalência (PE) / Análise de Valor Limite (AVL) no termo de busca:
        // PE: Inválida (termo muito curto: < 3 chars)
        if (!termoLowerCase.isEmpty() && termoLowerCase.length() < 3) {
            throw new IllegalArgumentException("O termo de busca deve ter pelo menos 3 caracteres.");
        }

        return obras.stream()
                .filter(obra -> {
                    boolean matchTermo = true;
                    if (!termoLowerCase.isEmpty()) {
                        // Busca tanto no título quanto no NOME do Autor
                        matchTermo = obra.getTitulo().toLowerCase().contains(termoLowerCase) ||
                                obra.getAutor().getNome().toLowerCase().contains(termoLowerCase);
                    }

                    boolean matchGenero = true;
                    if (!generoLowerCase.isEmpty()) {
                        matchGenero = obra.getGenero().toLowerCase().equals(generoLowerCase);
                    }

                    return matchTermo && matchGenero;
                })
                .toList();
    }

}
