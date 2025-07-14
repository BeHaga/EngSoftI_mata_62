package br.biblioteca.command;

import br.biblioteca.repositorio.Repositorio;
import br.biblioteca.entidade.Usuario;

import java.util.List;

public class ConsultarNotificacaoCommand implements Command {
    @Override
    public void executar(String[] args) {
        System.out.println("ConsultarNotificacaoCommand chamado!");
        
        if (args.length < 1) {
            System.out.println("Uso: ntf <codigo_usuario>");
            return;
        }

        String codUsuario = args[0];
        Repositorio repo = Repositorio.getInstancia();

        Usuario usuario = repo.buscarUsuarioPorCodigo(codUsuario);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        List<String> notificacoes = usuario.getNotificacoes();

        System.out.println("===== Notificações de " + usuario.getNome() + "=====");

        if (notificacoes.isEmpty()) {
            System.out.println("Nenhuma notificação pendente.");
        } else {
            for (String n : notificacoes) {
                System.out.println("- " + n);
            }
            usuario.limparNotificacoes();
        }
        
        System.out.println("=========================================");
    }
}