package br.biblioteca.command;

import br.biblioteca.repositorio.Repositorio;
import br.biblioteca.entidade.*;
import br.biblioteca.console.LeituraEscrita;

import java.util.List;

public class ConsultarLivroCommand implements Command {
    @Override
    public void executar(String[] args) {

        LeituraEscrita console = LeituraEscrita.getInstancia();
        
        if (args.length < 1) {
            console.mostrarMensagem("Uso: liv <codigo_livro>");
            return;
        }

        String codLivro = args[0];
        Repositorio repo = Repositorio.getInstancia();

        Livro livro = repo.buscarLivroPorCodigo(codLivro);
        if (livro == null) {
            console.mostrarMensagem("Livro não encontrado.");
            return;
        }

        console.mostrarMensagem("===== Informações do Livro =====");
        console.mostrarMensagem("Código: " + livro.getCodigo());
        console.mostrarMensagem("Título: " + livro.getCodigo());
        console.mostrarMensagem("Autor(es): " + livro.getAutores());
        console.mostrarMensagem("Editora: " + livro.getEditora());
        console.mostrarMensagem("Edição: " + livro.getEdicao());
        console.mostrarMensagem("Ano de Publicação: " + livro.getAnoPublicacao());

        List<Exemplar> exemplares = livro.getExemplares();
        int totalExemplares = exemplares.size();
        long disponiveis = exemplares.stream()
            .filter(e -> e.getStatus() == StatusExemplar.DISPONIVEL)
            .count();

        console.mostrarMensagem("Exemplares totais: " + totalExemplares);
        console.mostrarMensagem("Exemplares disponíveis: " + disponiveis);

        List<Reserva> reservas = livro.getReservas();
        console.mostrarMensagem("Reservas ativas: " + reservas.size());
        if (!reservas.isEmpty()) {
            console.mostrarMensagem("Fila de reservas: ");
            for (int i = 0; i < reservas.size(); i++) {
                Reserva r = reservas.get(i);
                console.mostrarMensagem((i + 1) + ". " + r.getUsuario().getNome() + " (" + r.getUsuario().getCodigo() + ")");   
            }
        }
        console.mostrarMensagem("===============================");
    }
}