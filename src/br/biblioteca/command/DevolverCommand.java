package br.biblioteca.command;
import java.util.List;

public class DevolverCommand implements Command {
    @Override
    public void executar(String[] args) {

        if (args.length < 2) {
            System.out.println("Uso: emp <usuario> <livro>");
            return;
        }

        Repositorio repo = Repositorio.getInstancia();
        String codigoUsuario = args[0];
        String codigoLivro = args[1];

        Usuario usuario = repo.buscarUsuarioPorCodigo(codigoUsuario);
        Livro livro = repo.buscarLivroPorCodigo(codigoLivro);

        List <Emprestimo> emprestimos = usuario.getEmprestimos();

        for (Emprestimo emprestimo : emprestimos) {
            
            if (emprestimo.getExemplar().getLivro().equals(livro)) {

                //Falta a logica de devolver nas classes usuario e emprestimo

                System.out.println("\nDevolução realizada: " + usuario.getNome() + " devolveu o livro: "+ livro.getTitulo());
                
                return;
            }
        }
    
        System.out.println("Usuario não possui emprestimos em aberto para esse livro.");
    
    }
}