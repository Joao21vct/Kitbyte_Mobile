package com.example.projetointerdisciplinar;

import java.util.Date;

public class Usuario {
    private int id;
    private Date data_nascimento;
    private String senha;
    private int pontos;
    private String nome_usuario;
    private String nome_real;
    private int num_kitcoins;
    private String email;
    private int fk_plano_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getNome_real() {
        return nome_real;
    }

    public void setNome_real(String nome_real) {
        this.nome_real = nome_real;
    }

    public int getNum_kitcoins() {
        return num_kitcoins;
    }

    public void setNum_kitcoins(int num_kitcoins) {
        this.num_kitcoins = num_kitcoins;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFk_plano_id() {
        return fk_plano_id;
    }

    public void setFk_plano_id(int fk_plano_id) {
        this.fk_plano_id = fk_plano_id;
    }

    public Usuario(int id, Date data_nascimento, String senha, int pontos, String nome_usuario, String nome_real, int num_kitcoins, String email, int fk_plano_id) {
        this.id = id;
        this.data_nascimento = data_nascimento;
        this.senha = senha;
        this.pontos = pontos;
        this.nome_usuario = nome_usuario;
        this.nome_real = nome_real;
        this.num_kitcoins = num_kitcoins;
        this.email = email;
        this.fk_plano_id = fk_plano_id;
    }

    public Usuario(Usuario usuario) {
        this.id = usuario.getId();
        this.data_nascimento = usuario.getData_nascimento();
        this.senha = usuario.getSenha();
        this.pontos = usuario.getPontos();
        this.nome_usuario = usuario.getNome_usuario();
        this.nome_real = usuario.getNome_real();
        this.num_kitcoins = usuario.getNum_kitcoins();
        this.email = usuario.getEmail();
        this.fk_plano_id = usuario.getFk_plano_id();
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", data_nascimento=" + data_nascimento +
                ", senha='" + senha + '\'' +
                ", pontos=" + pontos +
                ", nome_usuario='" + nome_usuario + '\'' +
                ", nome_real='" + nome_real + '\'' +
                ", num_kitcoins=" + num_kitcoins +
                ", email='" + email + '\'' +
                ", fk_plano_id=" + fk_plano_id +
                '}';
    }
}
