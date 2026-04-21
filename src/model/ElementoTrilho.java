package model;

public abstract class ElementoTrilho implements Elemento {
   protected int posicao;

    
    public ElementoTrilho(int posicao) {
        this.posicao = posicao;
    }

    
    public abstract void exibir();
    public int getPosicao() {
        return posicao;
    } 
}
