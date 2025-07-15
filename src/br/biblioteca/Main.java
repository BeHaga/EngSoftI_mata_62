package br.biblioteca;

import br.biblioteca.console.ConsoleInvoker;
import br.biblioteca.command.*;
import br.biblioteca.console.LeituraEscrita;

public class Main {
    public static void main(String[] args) {

        LeituraEscrita console = LeituraEscrita.getInstancia();

        ConsoleInvoker invoker = new ConsoleInvoker();

        invoker.registrarComando("emp", new EmprestarCommand());
        invoker.registrarComando("dev", new DevolverCommand());
        invoker.registrarComando("res", new ReservarCommand());
        invoker.registrarComando("liv", new ConsultarLivroCommand());
        invoker.registrarComando("usu", new ConsultarUsuarioCommand());
        invoker.registrarComando("ntf", new ConsultarNotificacaoCommand());
        invoker.registrarComando("obs", new RegistrarObservadorCommand());

        console.mostrarMensagem("Funcionando!");

        invoker.iniciar();
    }
}