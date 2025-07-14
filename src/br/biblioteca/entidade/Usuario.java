package br.biblioteca.entidade;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String codigo;
    private TipoUsuario tipoUsuario;
    private String nome;
    private List<Emprestimo> emprestimosAtivos;
    private List<Reserva> reservas;
    private List<String> notificacoes = new ArrayList<>();

    public Usuario(String codigo, TipoUsuario tipoUsuario, String nome) {
        this.codigo = codigo;
        this.tipoUsuario = tipoUsuario;
        this.nome = nome;
        this.emprestimosAtivos = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public String getNome() {
        return nome;
    }

    public List<Emprestimo> getEmprestimosAtivos() {
        return emprestimosAtivos;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void adicionarEmprestimo(Emprestimo e) {
        emprestimosAtivos.add(e);
    }

    public void adicionarReserva(Reserva r) {
      reservas.add(r);
    }

    public void removerReserva(Livro livro) {
        for (Reserva r : reservas) {
            if (r.getLivro().getCodigo().equals(livro.getCodigo())) {
                reservas.remove(r);
                break;
            }
        }
    }

    public int getLimiteEmprestimos() {
        switch (tipoUsuario) {
            case ALUNO_GRADUACAO:
                return 2;
            case ALUNO_POS:
                return 3;
            case PROFESSOR:
                return Integer.MAX_VALUE;
            default:
                return 0;
        }
    }

    public boolean podeEmprestar() {
        return emprestimosAtivos.size() < getLimiteEmprestimos();
    }

    public boolean isDevedor() {
        Date hoje = new Date();
        for (Emprestimo e : emprestimosAtivos) {
            if (e.getDataDevolucaoEfetiva() == null && hoje.after(e.getDataDevolucao())) {
                return true;
            }
        }
        return false;
    }

    public void removerEmprestimo(Emprestimo emprestimoAntigo){
        
        for (Emprestimo emprestimo : emprestimosAtivos){
            if (emprestimo.equals(emprestimoAntigo)){
                this.emprestimosAtivos.remove(emprestimo);
                emprestimoAntigo.setDataDevolucaoEfetiva(new Date());
                return;
            }
        }
    }

    public int quantidadeReservas(){
        return this.reservas.size();
    }

    public boolean temReserva(Livro livro){
        for (Reserva r : reservas) {
            if (r.getLivro().getCodigo().equals(livro.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public List<String> getNotificacoes() {
        return notificacoes;
    }

    public void adicionarNotificacao(String notificacao) {
        notificacoes.add(notificacao);
    }

    public void limparNotificacoes() {
        notificacoes.clear();
    }


}