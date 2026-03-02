package model;

public class Trem {
    private int velocidade;
    private int passageiros;
    private int posicaoAtual;

    public Trem(int velocidade, int passageiros, int posicaoInicial) {
        this.velocidade = velocidade;
        this.passageiros = passageiros;
        this.posicaoAtual = posicaoInicial;
    }

    public void mover() {
        posicaoAtual += velocidade;
        System.out.println("O trem está se movendo para a posição: " + posicaoAtual);
    }

    public void embarcar(int quantidade) {
        passageiros += quantidade;
        System.out.println(quantidade + " passageiros embarcaram. Total: " + passageiros);
    }

    public void desembarcar(int quantidade) {
        passageiros -= quantidade;
        System.out.println(quantidade + " passageiros desembarcaram. Total: " + passageiros);
    }

    public int getPosicaoAtual() {
        return posicaoAtual;
    }
}
