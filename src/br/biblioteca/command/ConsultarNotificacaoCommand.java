package br.biblioteca.command;

import br.biblioteca.repositorio.Repositorio;
import br.biblioteca.entidade.Usuario;
import br.biblioteca.entidade.Observer;
import br.biblioteca.console.LeituraEscrita;

import java.util.List;

public class ConsultarNotificacaoCommand implements Command {
    @Override
    public void executar(String[] args) {

        LeituraEscrita console = LeituraEscrita.getInstancia();
        
        if (args.length < 1) {
            console.mostrarMensagem("Uso: ntf <codigo_usuario>");
            return;
        }

        String codUsuario = args[0];

        Repositorio repo = Repositorio.getInstancia();

        Usuario usuario = repo.buscarUsuarioPorCodigo(codUsuario);

        if (usuario == null) {
            console.mostrarMensagem("Usuário não encontrado.");
            return;
        }

        Observer observer = (Observer) usuario;

        console.mostrarMensagem("===== Notificações de " + usuario.getNome() + "=====");

        if (observer.getNotificacoes() == 0) {
            console.mostrarMensagem("Zero livros notificados.");
        } else {
            console.mostrarMensagem("Quantidade de livros notificados: " + observer.getNotificacoes());
        }
        
        console.mostrarMensagem("=========================================");
    }
}