package br.biblioteca.entidade;

public class Livro {
    private String codigo;
    private String titulo;

    public Livro(String codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }
}