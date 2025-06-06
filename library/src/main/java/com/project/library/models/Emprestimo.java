
package com.project.library.models;


import java.time.LocalDate;

public class Emprestimo {
    private int id;
    private int livroId;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private String nomeLocatario;
    private String livroTitulo; 

    public Emprestimo() {}

    public Emprestimo(int livroId, LocalDate dataEmprestimo, String nomeLocatario) {
        this.livroId = livroId;
        this.dataEmprestimo = dataEmprestimo;
        this.nomeLocatario = nomeLocatario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLivroId() {
        return livroId;
    }

    public void setLivroId(int livroId) {
        this.livroId = livroId;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getNomeLocatario() {
        return nomeLocatario;
    }

    public void setNomeLocatario(String nomeLocatario) {
        this.nomeLocatario = nomeLocatario;
    }

    public String getLivroTitulo() {
        return livroTitulo;
    }

    public void setLivroTitulo(String livroTitulo) {
        this.livroTitulo = livroTitulo;
    }

    
}