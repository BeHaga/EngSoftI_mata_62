package br.biblioteca.command;

import br.biblioteca.repositorio.Repositorio;
import br.biblioteca.entidade.*;

import java.util.List;

public class ConsultarLivroCommand implements Command {
    @Override
    public void executar(String[] args) {
        System.out.println("ConsultarLivroCommand chamado!");
        
        if (args.length < 1) {
            System.out.println("Uso: liv <codigo_livro>");
            return;
        }

        String codLivro = args[0];
        Repositorio repo = Repositorio.getInstancia();

        Livro livro = repo.buscarLivroPorCodigo(codLivro);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        System.out.println("===== Informações do Livro =====");
        System.out.println("Código: " + livro.getCodigo());
        System.out.println("Título: " + livro.getCodigo());
        System.out.println("Autor(es): " + livro.getAutores());
        System.out.println("Editora: " + livro.getEditora());
        System.out.println("Edição: " + livro.getEdicao());
        System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());

        List<Exemplar> exemplares = livro.getExemplares();
        int totalExemplares = exemplares.size();
        long disponiveis = exemplares.stream()
            .filter(e -> e.getStatus() == StatusExemplar.DISPONIVEL)
            .count();

        System.out.println("Exemplares totais: " + totalExemplares);
        System.out.println("Exemplares disponíveis: " + disponiveis);

        List<Reserva> reservas = livro.getReservas();
        System.out.println("Reservas ativas: " + reservas.size());
        if (!reservas.isEmpty()) {
            System.out.println("Fila de reservas: ");
            for (int i = 0; i < reservas.size(); i++) {
                Reserva r = reservas.get(i);
                System.out.println((i + 1) + ". " + r.getUsuario().getNome() + " (" + r.getUsuario().getCodigo() + ")");   
            }
        }
        System.out.println("===============================");
    }
}