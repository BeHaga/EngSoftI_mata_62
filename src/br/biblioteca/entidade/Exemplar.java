package br.biblioteca.entidade;

public class Exemplar {

    private int codigo;
    private StatusExemplar status;
    private Emprestimo emprestimo;
    private Livro livro;


    public Exemplar(int codigo, Livro livro){
        this.codigo = codigo;
        this.status = StatusExemplar.DISPONIVEL;
        this.emprestimo = null;
        this.livro = livro;
    }

    public int getCodigo(){
        return codigo;
    }

    public Livro getLivro(){
        return this.livro;
    }

    public Emprestimo getEmprestimo(){
        return this.emprestimo;
    }

    public StatusExemplar getStatus() {
        return status;
    }

    public void setStatus(StatusExemplar status) {
        this.status = status;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }
}