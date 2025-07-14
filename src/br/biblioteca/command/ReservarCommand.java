package br.biblioteca.command;

import br.biblioteca.entidade.*;
import br.biblioteca.repositorio.Repositorio;

import java.util.Date;

public class ReservarCommand implements Command {
    @Override
    public void executar(String[] args) {

        if (args.length < 2) {
            System.out.println("Uso: emp <usuario> <livro>");
            return;
        }

        String codigoUsuario = args[0];
        String codigoLivro = args[1];

        Repositorio repo = Repositorio.getInstancia();

        Livro livro = repo.buscarLivroPorCodigo(codigoLivro);

        Usuario usuario = repo.buscarUsuarioPorCodigo(codigoUsuario);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }


        Date data = new Date();

        if(usuario.quantidadeReservas() < 3){

            if(!usuario.temReserva(livro) && livro.quantidadeExemplares() < livro.quantidadeExemplares()){

                Reserva reserva = new Reserva(usuario, livro, data);

                usuario.adicionarReserva(reserva);

                livro.adicionarReserva(reserva);

                usuario.adicionarNotificacao("Reserva do livro '" + livro.getTitulo() + "'realizada com sucesso.");

                System.out.println("Reserva realizada com sucesso!");
                System.out.println("Usuário: " + usuario.getNome());
                System.out.println("Livro: " + livro.getTitulo());
                System.out.println("Data da reserva: " + data);  

            } else if(usuario.temReserva(livro)) {
                System.out.println("Reserva não realizada: Usuario já reservou esse livro");
            } else {
                System.out.println("Reserva não realizada: Livro indisponivel");
            }

        } else{
            System.out.println("Reserva não realizada: Usuário já possui o limite de 3 reservas!");
        }



    }
}