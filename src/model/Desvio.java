package model;

import utils.StaticStack;

public class Desvio {
    private StaticStack<Trem> pilha = new StaticStack<>(10);

    public void receberTrem(Trem trem) throws Exception {
        System.out.println("Trem aguardando no DESVIO...");
        pilha.push(trem);
    }

    public Trem liberarTrem() throws Exception {
        System.out.println("Trem saindo do DESVIO...");
        return pilha.pop();
    }

}
