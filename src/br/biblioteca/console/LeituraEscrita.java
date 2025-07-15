package br.biblioteca.console;

import java.util.Scanner;

public class LeituraEscrita {

    private static LeituraEscrita instancia;
    private Scanner scanner;

    private LeituraEscrita() {
        scanner = new Scanner(System.in);
    }

    public static LeituraEscrita getInstancia() {
        if (instancia == null) {
            instancia = new LeituraEscrita();
        }
        return instancia;
    }

    public String lerComando() {
        return scanner.nextLine();
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}