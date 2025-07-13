import java.util.Date;

class Reserva {

    private Usuario usuario;
    private Livro livro;
    private Date dataReserva;

    public Reserva(Usuario usuario, Livro livro, Date dataReserva){
        this.usuario = usuario;
        this.livro = livro;
        this.dataReserva = dataReserva;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public Livro getLivro() {
        return this.livro;
    }

    public Date getData(){
        return this.dataReserva;
    }



}