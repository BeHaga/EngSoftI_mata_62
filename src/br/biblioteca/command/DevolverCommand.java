package br.biblioteca.command;

import br.biblioteca.entidade.*;
import br.biblioteca.repositorio.Repositorio;

import java.util.Date;
import java.util.List;

public class DevolverCommand implements Command {
    @Override
    public void executar(String[] args) {

        if (args.length < 2) {
            System.out.println("Uso: emp <usuario> <livro>");
            return;
        }
        String codigoUsuario = args[0];
        String codigoLivro = args[1];

        Repositorio repo = Repositorio.getInstancia();
        Usuario usuario = repo.buscarUsuarioPorCodigo(codigoUsuario);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        Livro livro = repo.buscarLivroPorCodigo(codigoLivro);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
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
            System.out.println("Nenhum empréstimo ativo deste livro encontrado.");
            return;
        }

        Date hoje = new Date();
        emprestimoParaDevolver.setDataDevolucaoEfetiva(hoje);

        Exemplar exemplar = emprestimoParaDevolver.getExemplar();
        exemplar.setStatus(StatusExemplar.DISPONIVEL);
        exemplar.setEmprestimo(null);

        usuario.removerEmprestimo(emprestimoParaDevolver);

        System.out.println("Devolução realizada com sucesso!");
        System.out.println("Usuário: " + usuario.getNome());
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Exemplar: " + exemplar.getCodigo());
        System.out.println("Data de devolução: " + hoje);    

        List<Reserva> reservas = livro.getReservas();
        if (!reservas.isEmpty()) {
            Reserva proximaReserva = reservas.get(0);
            Usuario usuarioReservante = proximaReserva.getUsuario();
            usuarioReservante.adicionarNotificacao("O livro '" + livro.getTitulo() + "' está disponível para retirada.");
        }
    }
}