package br.biblioteca.command;

import br.biblioteca.repositorio.Repositorio;
import br.biblioteca.entidade.*;
import br.biblioteca.console.LeituraEscrita;

import java.util.Date;

public class EmprestarCommand implements Command {
    @Override
    public void executar(String[] args) {

        LeituraEscrita console = LeituraEscrita.getInstancia();
        
        if (args.length < 2) {
            console.mostrarMensagem("Uso: emp <usuario> <livro>");
            return;
        }

        String codUsuario = args[0];
        String codLivro = args[1];

        Repositorio repo = Repositorio.getInstancia();

        Usuario usuario = repo.buscarUsuarioPorCodigo(codUsuario);

        if (usuario == null) {
            console.mostrarMensagem("Usuário não encontrado.");
            return;
        }

        if (usuario.isDevedor()) {
            usuario.adicionarNotificacao("Você possui empréstimos em atraso. Regularize para realizar novos empréstimos.");
            console.mostrarMensagem("Usuário possui empréstimos em atraso e não pode realizar novos empréstimos.");
            return;
        }

        if (!usuario.podeEmprestar()) {
            console.mostrarMensagem("Usuário atingiu o limite de empréstimos");
            return;
        }

        Livro livro = repo.buscarLivroPorCodigo(codLivro);

        if (livro == null) {
            console.mostrarMensagem("Livro não encontrado.");
            return;
        }

        Exemplar exemplarDisponivel = livro.getPrimeiroExemplarDisponivel();
        
        if (exemplarDisponivel == null) {
            console.mostrarMensagem("Não há exemplares disponíveis deste livro para empréstimo.");
            return;
        }

        Date hoje = new Date();
        Date dataPrevistaDevolucao = Emprestimo.calcularDataPrevista(usuario, hoje);
        Emprestimo emprestimo = new Emprestimo(usuario, exemplarDisponivel, hoje, dataPrevistaDevolucao);

        exemplarDisponivel.setStatus(StatusExemplar.EMPRESTADO);
        exemplarDisponivel.setEmprestimo(emprestimo);
        usuario.adicionarEmprestimo(emprestimo);
        repo.adicionarEmprestimo(emprestimo);

        usuario.removerReserva(livro);
        livro.removerReserva(livro);

        console.mostrarMensagem("Empréstimo realizado com sucesso!");
        console.mostrarMensagem("Usuário: " + usuario.getNome());
        console.mostrarMensagem("Livro: " + livro.getTitulo());
        console.mostrarMensagem("Exemplar: " + exemplarDisponivel.getCodigo());
        console.mostrarMensagem("Data prevista de devolução: " + dataPrevistaDevolucao);
    }
}