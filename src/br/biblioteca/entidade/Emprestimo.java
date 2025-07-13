package br.biblioteca.entidade;

import java.util.Date;
import java.util.Calendar;
import br.biblioteca.entidade.Usuario;
import br.biblioteca.entidade.TipoUsuario;

public class Emprestimo{

    private Usuario usuario;
    private Exemplar exemplar;
    private Date dataEmprestimo, dataDevolucao, dataDevolucaoEfetiva;

    public Emprestimo(Usuario usuario, Exemplar exemplar, Date dataEmprestimo, Date dataDevolucao){
        this.usuario = usuario;
        this.exemplar = exemplar;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
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

    public static Date calcularDataPrevista(Usuario usuario, Date dataEmprestimo) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataEmprestimo);

        TipoUsuario tipo = usuario.getTipoUsuario();
        switch (tipo) {
            case ALUNO_GRADUACAO:
                calendar.add(Calendar.DAY_OF_MONTH, 7);
                break;
            case ALUNO_POS:                
                calendar.add(Calendar.DAY_OF_MONTH, 10);
                break;
            case PROFESSOR:                
                calendar.add(Calendar.DAY_OF_MONTH, 15);
                break;
            default:                
                calendar.add(Calendar.DAY_OF_MONTH, 7);
        }

        return calendar.getTime();
    }

}