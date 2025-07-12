package br.biblioteca.repositorio;

import br.biblioteca.entidade.Usuario;
import br.biblioteca.entidade.Livro;
import java.util.ArrayList;
import java.util.List;


public class Repositorio {
    // Para acessar os dados do repositório preciso usar:
    // Repositorio repo = Repositorio.getInstancia();
    // Usuario u = repo.buscarUsuarioPorCodigo("123");

    private static Repositorio instancia;

    private List<Usuario> usuarios = new ArrayList<>();
    private List<Livro> livros = new ArrayList<>();
    // private List<Exemplar> exemplares = new ArrayList<>();

    private Repositorio() {
        usuarios.add(new Usuario("123", "João da Silva"));
        usuarios.add(new Usuario("456", "Luiz Fernando Rodrigues"));
        livros.add(new Livro("101", "UML - Guia do Usuário"));
        livros.add(new Livro("100", "Engenharia de Software"));
    }

    public static Repositorio getInstancia() {
        if (instancia == null) {
            instancia = new Repositorio();
        }
        return instancia;
    }

    public Usuario buscarUsuarioPorCodigo(String codigo) {
        for (Usuario u : usuarios) {
            if (u.getCodigo().equals(codigo)) {
                return u;
            }
        }
        return null;
    }

    public Livro buscarLivroPorCodigo(String codigo) {
        for (Livro l : livros) {
            if (l.getCodigo().equals(codigo)) {
                return l;
            }
        }
        return null;
    }
}