package br.biblioteca.entidade;

public class PosGraduacao extends Usuario {
    public PosGraduacao(String codigo, String nome) {
        super(codigo, nome, 5, 3);
    }

    @Override
    public String getDescricaoTipo() {
        return "PosGraduação";
    }

    @Override
    public int getLimiteEmprestimos() {
        return 3;
    }
  
}
