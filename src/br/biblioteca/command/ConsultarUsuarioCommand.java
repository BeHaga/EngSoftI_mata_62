package br.biblioteca.command;

import br.biblioteca.repositorio.Repositorio;
import br.biblioteca.entidade.*;

import java.text.SimpleDateFormat;
import java.util.List;

public class ConsultarUsuarioCommand implements Command {
    @Override
    public void executar(String[] args) {
        System.out.println("ConsultarUsuarioCommand chamado!");
        
        if (args.length < 1) {
            System.out.println("Uso: usu <codigo_usuario>");
            return;
        }

        String codUsuario = args[0];
        Repositorio repo = Repositorio.getInstancia();

        Usuario usuario = repo.buscarUsuarioPorCodigo(codUsuario);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.println("===== Informações do Usuário =====");
        System.out.println("Código: " + usuario.getCodigo());
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Tipo: " + usuario.getTipoUsuario());
        System.out.println("----------------------------------");

        List<Emprestimo> emprestimos = usuario.getEmprestimosAtivos();
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo ativo.");
        } else {
            System.out.println("Empréstimos Ativos:");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (Emprestimo e : emprestimos) {
                System.out.println("- Livro: " + e.getExemplar().getLivro().getTitulo() +
                    " | Exemplar: " + e.getExemplar().getCodigo() +
                    " | Devolver até: " + sdf.format(e.getDataDevolucao()));
            }
        }
        
        List<Reserva> reservas = usuario.getReservas();
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva ativa.");
        } else {
            System.out.println("Reservas ativas:");
            for (Reserva r : reservas) {
                System.out.println("- Livro: " + r.getLivro().getTitulo() + 
                " | Código: " + r.getLivro().getCodigo());
            }
        }
        System.out.println("==================================");
    }
}