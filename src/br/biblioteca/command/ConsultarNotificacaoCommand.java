package br.biblioteca.command;

import br.biblioteca.repositorio.Repositorio;
import br.biblioteca.entidade.Usuario;
import br.biblioteca.console.LeituraEscrita;

import java.util.List;

public class ConsultarNotificacaoCommand implements Command {
    @Override
    public void executar(String[] args) {

        LeituraEscrita console = LeituraEscrita.getInstancia();

        console.mostrarMensagem("ConsultarNotificacaoCommand chamado!");
        
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

        List<String> notificacoes = usuario.getNotificacoes();

        console.mostrarMensagem("===== Notificações de " + usuario.getNome() + "=====");

        if (notificacoes.isEmpty()) {
            console.mostrarMensagem("Nenhuma notificação pendente.");
        } else {
            for (String n : notificacoes) {
                console.mostrarMensagem("- " + n);
            }
            usuario.limparNotificacoes();
        }
        
        console.mostrarMensagem("=========================================");
    }
}