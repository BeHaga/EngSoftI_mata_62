package br.bilioteca;

import br.biblioteca.console.ConsoleInvoker;
import br.biblioteca.command.EmprestarCommand;
//preciso lembrar de adicionar o import dos demais commands

public class Main {
    public static void Main(String[] args) {
        ConsoleInvoker invoker = new ConsoleInvoker();

        invoker.registrarComando("emp", new EmprestarCommand());
        //preciso lembrar de adicionar os demais comandos

        System.out.println("Funcionando!");

        invoker.iniciar();
    }
}