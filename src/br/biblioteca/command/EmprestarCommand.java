package br.biblioteca.command;

import br.biblioteca.repositorio.Repositorio;
import br.biblioteca.entidade.Usuario;
import br.biblioteca.entidade.Livro;

public class EmprestarCommand implements Command {
    @Override
    public void executar(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: emp <usuario> <livro>");
            return;
        }

        String codUsuario = args[0];
        String codLivro = args[1];

        Repositorio repo = Repositorio.getInstancia();
        Usuario usuario = repo.buscarUsuarioPorCodigo(codUsuario);
        Livro livro = repo.buscarLivroPorCodigo(codLivro);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        //Ainda vou implementar a lógica de empréstimo e checagem de regras
        //Tenho que lembrar que aqui tem que ter Strategy
    }
}