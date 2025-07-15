package br.biblioteca.entidade;

public class Graduacao extends Usuario {
    public Graduacao(String codigo, String nome) {
        super(codigo, nome, 4, 2);
    }

    @Override
    public String getDescricaoTipo() {
        return "Graduação";
    }

    @Override
    public int getLimiteEmprestimos() {
        return 2;
    }
  
}