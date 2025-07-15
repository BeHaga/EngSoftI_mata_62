package br.biblioteca.repositorio;

import br.biblioteca.entidade.*;
import java.util.ArrayList;
import java.util.List;

public class Repositorio {
    // Para acessar os dados do repositório preciso usar:
    // Repositorio repo = Repositorio.getInstancia();
    // Usuario u = repo.buscarUsuarioPorCodigo("123");

    private static Repositorio instancia;

    private List<Usuario> usuarios;
    private List<Livro> livros;
    private List<Emprestimo> emprestimos;
    private List<Reserva> reservas;

    // private List<Exemplar> exemplares = new ArrayList<>();

    private Repositorio() {
        usuarios = new ArrayList<>();
        livros = new ArrayList<>();
        emprestimos = new ArrayList<>();
        reservas = new ArrayList<>();

        popularDados();
    }

    public static Repositorio getInstancia() {
        if (instancia == null) {
            instancia = new Repositorio();
        }
        return instancia;
    }

    private void popularDados() {
        usuarios.add(new Graduacao("123", "João da Silva"));
        usuarios.add(new PosGraduacao("456", "Luiz Fernando Rodrigues"));
        usuarios.add(new Graduacao("789", "Pedro Paulo"));
        usuarios.add(new Professor("100", "Carlos Lucena"));

        Livro livro1 = new Livro("100", "Engenharia de Software", "Addison Wesley", "Ian Sommerville", "6", 2000);
        Livro livro2 = new Livro("101", "UML - Guia do Usuário", "Campus", "Grady Booch, James Rumbaugh, Ivar Jacobson", "7", 2000);
        Livro livro3 = new Livro("200", "Code Complete", "Microsoft Press", "Steve McConnell", "2", 2014);
        Livro livro4 = new Livro("201", "Agile Software Development, Principles, Patterns and Practices", "Prentice Hall", "Robert Martin", "1", 2002);
        Livro livro5 = new Livro("300", "Refactoring: Improving the Design of Existing Code", "Addison Wesley Professional", "Martin Fowler", "1", 1999);
        Livro livro6 = new Livro("301", "Software Metrics: A rigorous and Practical Approach", "CRC Press", "Norman Fenton, James Bieman", "3", 2014);
        Livro livro7 = new Livro("400", "Design Patterns: Element of Reusable Object-Oriented Software", "Addison Wesley Professional", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "1", 1994);
        Livro livro8 = new Livro("401", "UML Distilled: A Brief Guide to the Standard Object Modeling Language", "Addison Wesley Professional", "Martin Fowler", "3", 2003);

        livro1.adicionarExemplar(new Exemplar(1, livro1));
        livro1.adicionarExemplar(new Exemplar(2, livro1));
        livro2.adicionarExemplar(new Exemplar(3, livro2));
        livro3.adicionarExemplar(new Exemplar(4, livro3));
        livro4.adicionarExemplar(new Exemplar(5, livro4));
        livro5.adicionarExemplar(new Exemplar(6, livro5));
        livro6.adicionarExemplar(new Exemplar(7, livro6));
        livro7.adicionarExemplar(new Exemplar(8, livro7));
        livro8.adicionarExemplar(new Exemplar(9, livro8));

        livros.add(livro1);
        livros.add(livro2);
        livros.add(livro3);
        livros.add(livro4);
        livros.add(livro5);
        livros.add(livro6);
        livros.add(livro7);
        livros.add(livro8);
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

    public void adicionarEmprestimo(Emprestimo e) {
        emprestimos.add(e);
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void adicionarReserva(Reserva r) {
        reservas.add(r);
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

}