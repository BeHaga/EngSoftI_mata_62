package br.biblioteca.command;

import br.biblioteca.entidade.*;
import br.biblioteca.repositorio.Repositorio;
import br.biblioteca.console.LeituraEscrita;

import java.util.Date;
import java.util.List;

public class DevolverCommand implements Command {
    @Override
    public void executar(String[] args) {

        LeituraEscrita console = LeituraEscrita.getInstancia();

        if (args.length < 2) {
            console.mostrarMensagem("Uso: emp <usuario> <livro>");
            return;
        }
        String codigoUsuario = args[0];
        String codigoLivro = args[1];

        Repositorio repo = Repositorio.getInstancia();
        Usuario usuario = repo.buscarUsuarioPorCodigo(codigoUsuario);
        if (usuario == null) {
            console.mostrarMensagem("Usuário não encontrado.");
            return;
        }

        Livro livro = repo.buscarLivroPorCodigo(codigoLivro);
        if (livro == null) {
            console.mostrarMensagem("Livro não encontrado.");
            return;
        }

        List<Emprestimo> emprestimos = usuario.getEmprestimosAtivos();
        Emprestimo emprestimoParaDevolver = null;

        for (Emprestimo emprestado : emprestimos) {
            
            if (emprestado.getExemplar().getLivro().equals(livro) && emprestado.getDataDevolucaoEfetiva() == null) {
                emprestimoParaDevolver = emprestado;
                break;
            }
        }

        if (emprestimoParaDevolver == null) {
            console.mostrarMensagem("Nenhum empréstimo ativo deste livro encontrado.");
            return;
        }

        Date hoje = new Date();
        emprestimoParaDevolver.setDataDevolucaoEfetiva(hoje);

        Exemplar exemplar = emprestimoParaDevolver.getExemplar();
        
        exemplar.setStatus(StatusExemplar.DISPONIVEL);
        exemplar.setEmprestimo(null);

        usuario.removerEmprestimo(emprestimoParaDevolver);

        console.mostrarMensagem("Devolução realizada com sucesso!");
        console.mostrarMensagem("Usuário: " + usuario.getNome());
        console.mostrarMensagem("Livro: " + livro.getTitulo());
        console.mostrarMensagem("Exemplar: " + exemplar.getCodigo());
        console.mostrarMensagem("Data de devolução: " + hoje);    

    }
}