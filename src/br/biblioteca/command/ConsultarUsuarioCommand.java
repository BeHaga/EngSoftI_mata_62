package br.biblioteca.command;

import br.biblioteca.repositorio.Repositorio;
import br.biblioteca.entidade.*;
import br.biblioteca.console.LeituraEscrita;

import java.text.SimpleDateFormat;
import java.util.List;

public class ConsultarUsuarioCommand implements Command {
    @Override
    public void executar(String[] args) {

        LeituraEscrita console = LeituraEscrita.getInstancia();
        
        if (args.length < 1) {
            console.mostrarMensagem("Uso: usu <codigo_usuario>");
            return;
        }

        String codUsuario = args[0];
        Repositorio repo = Repositorio.getInstancia();

        Usuario usuario = repo.buscarUsuarioPorCodigo(codUsuario);
        if (usuario == null) {
            console.mostrarMensagem("Usuário não encontrado.");
            return;
        }

        console.mostrarMensagem("===== Informações do Usuário =====");
        console.mostrarMensagem("Código: " + usuario.getCodigo());
        console.mostrarMensagem("Nome: " + usuario.getNome());
        console.mostrarMensagem("Tipo: " + usuario.getDescricaoTipo());
        console.mostrarMensagem("----------------------------------");

        List<Emprestimo> emprestimos = usuario.getEmprestimosAtivos();
        if (emprestimos.isEmpty()) {
            console.mostrarMensagem("Nenhum empréstimo ativo.");
        } else {
            console.mostrarMensagem("Empréstimos Ativos:");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (Emprestimo e : emprestimos) {
                console.mostrarMensagem("- Livro: " + e.getExemplar().getLivro().getTitulo() +
                    " | Exemplar: " + e.getExemplar().getCodigo() +
                    " | Devolver até: " + sdf.format(e.getDataDevolucao()));
            }
        }
        
        List<Reserva> reservas = usuario.getReservas();
        if (reservas.isEmpty()) {
            console.mostrarMensagem("Nenhuma reserva ativa.");
        } else {
            console.mostrarMensagem("Reservas ativas:");
            for (Reserva r : reservas) {
                console.mostrarMensagem("- Livro: " + r.getLivro().getTitulo() + 
                " | Código: " + r.getLivro().getCodigo());
            }
        }
        console.mostrarMensagem("==================================");
    }
}