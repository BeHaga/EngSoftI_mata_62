package br.biblioteca.entidade;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String codigo;
    private String nome;
    private int prazoDias;
    private int limiteLivros;
    private List<Emprestimo> emprestimosAtivos;
    private List<Reserva> reservas;

    public Usuario(String codigo, String nome, int prazoDias, int limiteLivros) {
        this.codigo = codigo;
        this.nome = nome;
        this.prazoDias = prazoDias;
        this.limiteLivros = limiteLivros; 
        this.emprestimosAtivos = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getPrazoDias(){
        return prazoDias;
    }

    public int getLivros(){
        return limiteLivros;
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

    public boolean verificarEmprestimo(Livro livro) {
        for (Emprestimo e : emprestimosAtivos) {
            if (e.getExemplar().getLivro().getCodigo().equals(livro.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    public int getLimiteEmprestimos(){
        return 0;
    }

    public boolean podeEmprestar() {
        return emprestimosAtivos.size() < getLimiteEmprestimos();
    }

    public boolean isDevedor() {
        Date hoje = new Date();
        for (Emprestimo e : emprestimosAtivos) {
            if ((e.getDataDevolucaoEfetiva() == null) && hoje.after(e.getDataDevolucao())) {
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

    public boolean podeIgnorarFilaDeReserva() {
        return false;
    }

    public String getDescricaoTipo(){
        return "";
    }


}