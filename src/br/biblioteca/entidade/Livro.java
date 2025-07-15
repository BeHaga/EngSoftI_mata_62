package br.biblioteca.entidade;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    private String codigo;
    private String titulo;
    private String editora;
    private String autores;
    private String edicao;
    private int anoPublicacao;
    private List<Exemplar> exemplares;
    private List<Reserva> reservas;
    private List<Observer> observers;

    public Livro(String codigo, String titulo, String editora, String autores, String edicao, int anoPublicacao) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.edicao = edicao;
        this.anoPublicacao = anoPublicacao;
        this.exemplares = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEditora() {
        return editora;
    }

    public String getAutores() {
        return autores;
    }

    public String getEdicao() {
        return edicao;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public List<Exemplar> getExemplares() {
        return exemplares;
    }

    public int quantidadeExemplares(){
        return exemplares.size();
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public int quantidadeReservas(){
        return reservas.size();
    }

    public void adicionarExemplar(Exemplar e) {
        exemplares.add(e);
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

    public boolean temExemplarDisponivel() {
        for (Exemplar e : exemplares) {
            if (e.getStatus() == StatusExemplar.DISPONIVEL) {
                return true;
            }
        }
        return false;
    }

    public Exemplar getPrimeiroExemplarDisponivel() {
        for (Exemplar e : exemplares) {
            if (e.getStatus() == StatusExemplar.DISPONIVEL) {
                return e;
            }
        }
        return null;
    }

    public void devolverExemplar(){
        for (Exemplar exemplar : exemplares) {

            if (!exemplar.isDisponivel()) {
                exemplar.devolver();
                break;
            }
        }
    }

    public void registrarObservador(Observer observer){
        observers.add(observer);
    }

    public void notificarObservadores(){
        if (this.quantidadeReservas()>=2){
            observers.forEach(o -> o.adicionarNotificacao());
        }
    }



}