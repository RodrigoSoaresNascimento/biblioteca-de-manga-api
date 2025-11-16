package com.mangasapi.biblioteca.model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public class Autor {

    @Min(1)
    private Long id;
    @NotNull
    @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres.")
    private String nome;

    public Autor(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Autor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
