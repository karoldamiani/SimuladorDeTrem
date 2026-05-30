package model;

import utils.StaticStack;

public class Desvio implements Elemento {
    private StaticStack<Trem> pilha = new StaticStack<>(10);

    public void receberTrem(Trem trem) throws Exception {
        System.out.println("Trem aguardando no DESVIO");
        pilha.push(trem);
    }

    public Trem liberarTrem() throws Exception {
        System.out.println("Trem saindo do DESVIO");
        return pilha.pop();
    }

    private int posicao;

    public Desvio(int posicao) {
        this.posicao = posicao;
    }

    public Desvio() {
        this(0);
    }

    public int getPosicao() {
        return posicao;
    }

    @Override
    public String exibir() {
        return "Desvio (pos " + posicao + ")";
    }

}
