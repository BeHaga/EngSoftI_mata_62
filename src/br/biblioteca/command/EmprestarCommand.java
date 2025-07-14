package br.biblioteca.command;

import br.biblioteca.repositorio.Repositorio;
import br.biblioteca.entidade.*;
import java.util.Date;

public class EmprestarCommand implements Command {
    @Override
    public void executar(String[] args) {
        System.out.println("DevolverCommand chamado!");
        
        if (args.length < 2) {
            System.out.println("Uso: emp <usuario> <livro>");
            return;
        }

        String codUsuario = args[0];
        String codLivro = args[1];

        Repositorio repo = Repositorio.getInstancia();
        Usuario usuario = repo.buscarUsuarioPorCodigo(codUsuario);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        if (usuario.isDevedor()) {
            usuario.adicionarNotificacao("Você possui empréstimos em atraso. Regularize para realizar novos empréstimos.");
            System.out.println("Usuário possui empréstimos em atraso e não pode realizar novos empréstimos.");
            return;
        }

        if (!usuario.podeEmprestar()) {
            System.out.println("Usuário atingiu o limite de empréstimos");
            return;
        }

        Livro livro = repo.buscarLivroPorCodigo(codLivro);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        Exemplar exemplarDisponivel = livro.getPrimeiroExemplarDisponivel();
        if (exemplarDisponivel == null) {
            System.out.println("Não há exemplares disponíveis deste livro para empréstimo.");
            return;
        }

        Date hoje = new Date();
        Date dataPrevistaDevolucao = Emprestimo.calcularDataPrevista(usuario, hoje);
        Emprestimo emprestimo = new Emprestimo(usuario, exemplarDisponivel, hoje, dataPrevistaDevolucao);

        exemplarDisponivel.setStatus(StatusExemplar.EMPRESTADO);
        exemplarDisponivel.setEmprestimo(emprestimo);
        usuario.adicionarEmprestimo(emprestimo);
        repo.adicionarEmprestimo(emprestimo);

        System.out.println("Empréstimo realizado com sucesso!");
        System.out.println("Usuário: " + usuario.getNome());
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Exemplar: " + exemplarDisponivel.getCodigo());
        System.out.println("Data prevista de devolução: " + dataPrevistaDevolucao);
    }
}