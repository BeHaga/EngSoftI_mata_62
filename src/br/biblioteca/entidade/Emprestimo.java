package br.biblioteca.entidade;

import java.util.Date;
import java.util.Calendar;
import br.biblioteca.entidade.Usuario;

public class Emprestimo{

    private Usuario usuario;
    private Exemplar exemplar;
    private Date dataEmprestimo, dataDevolucao, dataDevolucaoEfetiva;

    public Emprestimo(Usuario usuario, Exemplar exemplar, Date dataEmprestimo){
        this.usuario = usuario;
        this.exemplar = exemplar;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = new Date(dataEmprestimo.getTime() + (usuario.getPrazoDias() * 24 * 60 * 60 * 1000));
        dataDevolucaoEfetiva = null;
    }

    public Usuario getUsuario(){
        return this.usuario;
    }

    public Exemplar getExemplar(){
        return this.exemplar;
    }

    public Date getDataEmprestimo(){
        return dataEmprestimo;
    }

    public Date getDataDevolucao(){
        return dataDevolucao;
    }

    public Date getDataDevolucaoEfetiva(){
        return dataDevolucaoEfetiva;
    }


    public boolean Atrasado(){
        return dataDevolucaoEfetiva.after(dataDevolucao);
    }

    public void setDataDevolucaoEfetiva(Date dataDevolucaoEfetiva){
        this.dataDevolucaoEfetiva = dataDevolucaoEfetiva;
    }


}