package br.biblioteca.command;

import br.biblioteca.entidade.*;
import br.biblioteca.repositorio.Repositorio;
import br.biblioteca.console.LeituraEscrita;

import java.util.Date;

public class ReservarCommand implements Command {
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

        Livro livro = repo.buscarLivroPorCodigo(codigoLivro);

        Usuario usuario = repo.buscarUsuarioPorCodigo(codigoUsuario);

        if (livro == null) {
            console.mostrarMensagem("Livro não encontrado.");
            return;
        }

        if (usuario == null) {
            console.mostrarMensagem("Usuário não encontrado.");
            return;
        }


        Date data = new Date();

        boolean verificar = usuario.temReserva(livro);

        if(usuario.quantidadeReservas() < 3){


            if((!verificar) && (livro.quantidadeReservas() < livro.quantidadeExemplares())){

                Reserva reserva = new Reserva(usuario, livro, data);

                usuario.adicionarReserva(reserva);

                livro.adicionarReserva(reserva);

                if(livro.quantidadeReservas() >= 2){
                    livro.notificarObservadores();
                }

                console.mostrarMensagem("Reserva realizada com sucesso!");
                console.mostrarMensagem("Usuário: " + usuario.getNome());
                console.mostrarMensagem("Livro: " + livro.getTitulo());
                console.mostrarMensagem("Data da reserva: " + data);  

            } else if(verificar) {
                console.mostrarMensagem("Reserva não realizada: Usuario já reservou esse livro");
            } else {
                console.mostrarMensagem("Reserva não realizada: Livro indisponivel");
            }

        } else{
            console.mostrarMensagem("Reserva não realizada: Usuário já possui o limite de 3 reservas!");
        }



    }
}