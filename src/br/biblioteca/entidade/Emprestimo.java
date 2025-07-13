import java.util.Date;

class Emprestimo{

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


}