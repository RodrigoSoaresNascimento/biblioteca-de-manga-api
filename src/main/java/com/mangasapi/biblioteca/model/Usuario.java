package com.mangasapi.biblioteca.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public class Usuario {

    @Min(1)
    private Long id;

    @NotNull
    private String nome;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 8, max = 30)
    private String senhaCriptografada;

    public Usuario(Long id, String nome, String email, String senhaCriptografada) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senhaCriptografada = senhaCriptografada;
    }

    public Usuario() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaCriptografada() {
        return senhaCriptografada;
    }

    public void setSenhaCriptografada(String senhaCriptografada) {
        this.senhaCriptografada = senhaCriptografada;
    }

    public boolean validarSenha(String senha) {
        return this.senhaCriptografada.equals(senha);
    }

}
