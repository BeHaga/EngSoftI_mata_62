public class Repositorio {
    // Para acessar os dados do repositório preciso usar:
    // Repositorio repo = Repositorio.getInstancia();
    // Usuario u = repo.buscarUsuarioPorCodigo("123");

    private static Repositorio instacia;

    private List<Usuario> usuarios = new ArrayList<>();
    private List<Livro> livros = new ArrayList<>();
    private List<Exemplar> exemplares = new ArrayList<>();

    private Repositorio() {}

    public static Repositorio getInstancia() {
        if (instancia == null) {
            instancia = new Repositorio();
        }
        return instacia;
    }

    public Usuario buscarUsuarioPorCodigo(String codigo) {
        for (Usuario u : usuarios) {
            if (u.getCodigo().equals(codigo)) {
                return u;
            }
        }
        return null;
    }

    public Livro buscarLivroPorCodigo(String codigo) {
        for (Livro l : livros) {
            if (l.getCodigo().equals(codigo)) {
                return l;
            }
        }
        return null;
    }
}