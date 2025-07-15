package br.biblioteca.console;

import br.biblioteca.command.Command;
// import br.biblioteca.console.LeituraEscrita;

import java.util.HashMap;
import java.util.Map;

public class ConsoleInvoker {

    LeituraEscrita console = LeituraEscrita.getInstancia();

    private Map<String, Command> comandos = new HashMap<>();

    public void registrarComando(String chave, Command comando) {
        comandos.put(chave, comando);
    }

    public void iniciar() {

        console.mostrarMensagem("Digite um comando ou 'sair' para encerrar: ");

        while (true) {
            System.out.print("> ");
            String linha = console.lerComando().trim();
            if (linha.equalsIgnoreCase("sair")) {
                break;
            }
            if (linha.isEmpty()) continue;

            String[] partes = linha.split(" ");
            String comandoChave = partes[0];
            String[] args = new String[partes.length - 1];
            System.arraycopy(partes, 1, args, 0, partes.length - 1);

            Command comando = comandos.get(comandoChave);
            if (comando != null) {
                comando.executar(args);
            } else {
                console.mostrarMensagem("Comando desconhecido: " + comandoChave);
            }
        }        
    }
}