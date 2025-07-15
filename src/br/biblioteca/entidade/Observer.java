package br.biblioteca.entidade;

public interface Observer {
    void adicionarNotificacao();
    int getNotificacoes();
    void limparNotificacoes();
}