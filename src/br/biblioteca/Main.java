package br.bilioteca;

import br.biblioteca.console.ConsoleInvoker;
import br.biblioteca.command.EmprestarCommand;
//preciso lembrar de adicionar o import dos demais commands

public class Main {
    public static void Main(String[] args) {
        ConsoleInvoker invoker = new ConsoleInvoker();

        invoker.registrarComando("emp", new EmprestarCommand());
        invoker.registrarComando("dev", new DevolverCommand());
        invoker.registrarComando("res", new ReservarCommand());
        invoker.registrarComando("liv", new ConsultarLivroCommand());
        invoker.registrarComando("usu", new ConsultarUsuarioCommand());
        invoker.registrarComando("ntf", new ConsultarNotificacaoCommand());

        System.out.println("Funcionando!");

        invoker.iniciar();
    }
}