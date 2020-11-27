package com.pim.unip.bitbank;

public class Cliente {
    private String cpf;
    private String dtNascimento;
    private String nome;
    private String email;
    private String senha;

    public Cliente() {
    }

    public Cliente(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public Cliente(String cpf, String dtNascimento, String nome, String email, String senha) {
        this.cpf = cpf;
        this.dtNascimento = dtNascimento;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
