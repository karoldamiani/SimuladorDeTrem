package model;

public abstract class ElementoTrilho {
   protected int posicao;

    // Construtor
    public ElementoTrilho(int posicao) {
        this.posicao = posicao;
    }

    // Métodos que podem ser sobrescritos pelas subclasses
    public abstract void exibir();
    public int getPosicao() {
        return posicao;
    } 
}
