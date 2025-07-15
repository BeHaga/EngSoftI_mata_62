package br.biblioteca.command;

import br.biblioteca.repositorio.Repositorio;
import br.biblioteca.entidade.*;
import br.biblioteca.console.LeituraEscrita;

public class RegistrarObservadorCommand implements Command {

    @Override
    public void executar(String[] args) {
        LeituraEscrita console = LeituraEscrita.getInstancia();

        Repositorio repo = Repositorio.getInstancia();

        String codigoUsuario = args[0];
        String codigoLivro = args[1];

        Livro livro = repo.buscarLivroPorCodigo(codigoLivro);

        Usuario usuario = repo.buscarUsuarioPorCodigo(codigoUsuario);

        Observer observer = (Observer) usuario;

        livro.registrarObservador(observer);
        console.mostrarMensagem(usuario.getNome() + " registrado como observador do livro " + livro.getTitulo());
    }
}