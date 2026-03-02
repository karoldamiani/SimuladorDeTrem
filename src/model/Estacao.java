package model;

public class Estacao extends ElementoTrilho {
    private String nome;

    public Estacao(int posicao, String nome) {
        super(posicao);
        this.nome = nome;
    }

    @Override
    public void exibir() {
        System.out.println("Estação: " + nome + " na posição " + posicao);
    }
}
