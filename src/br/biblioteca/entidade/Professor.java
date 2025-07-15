package br.biblioteca.entidade;

public class Professor extends Usuario implements Observer{

    int notificacoes = 0;

    public Professor(String codigo, String nome) {
        super(codigo, nome, 8, Integer.MAX_VALUE);
    }

    @Override
    public boolean podeIgnorarFilaDeReserva() {
        return true; 
    }

    @Override
    public String getDescricaoTipo() {
        return "Professor";
    }

    public int getNotificacoes() {
        return this.notificacoes;
    }

    public void adicionarNotificacao() {
        this.notificacoes+=1;
    }

    public void limparNotificacoes() {
        notificacoes = 0;
    }

    @Override
    public int getLimiteEmprestimos() {
        return Integer.MAX_VALUE;
    }
  
}