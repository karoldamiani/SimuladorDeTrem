package model;

public class Trecho implements Elemento {

    private int posicao;

    public Trecho(int posicao) {
        this.posicao = posicao;
    }

    public int getPosicao() {
        return posicao;
    }

    @Override
    public String exibir() {
        return "[ ]";
    }

    @Override
    public String toString() {
        return exibir();
    }
}