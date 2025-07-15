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

        boolean verificar = usuario.temReserva(livro);

        if((usuario.podeIgnorarFilaDeReserva() == false) &&(verificar==false) && (livro.quantidadeReservas() >= livro.quantidadeExemplares())){
            console.mostrarMensagem("Emprestimo não realizado: Todos os exemplares estão reservados");
            return;
        }

        boolean verificarEmprestimo = usuario.verificarEmprestimo(livro);

        if(verificarEmprestimo == true){
            console.mostrarMensagem("O usuário já tem um exemplar deste mesmo livro em empréstimo.");
            return;
        }

        Date hoje = new Date();
        Emprestimo emprestimo = new Emprestimo(usuario, exemplarDisponivel, hoje);

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
    }
}