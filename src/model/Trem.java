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

    // Modelo Recursão prof
    public int calcularTempoTotal(NoTrilho<Elemento> noAtual) {

        // Caso base
        if (noAtual == null) {
            return 0;
        }

        int tempoNoAtual = 1; // 1 km = 1 minuto

        if (noAtual.getElemento() instanceof Estacao) {
            Estacao estacao = (Estacao) noAtual.getElemento();

            int pessoas = estacao.getPessoasSubindo() + estacao.getPessoasDescendo();

            if (pessoas == 0) {
                tempoNoAtual += 1;
            } else {
                tempoNoAtual += (pessoas * 30) / 60;
            }
        }

        return tempoNoAtual + calcularTempoTotal(noAtual.getProximo());
    }

    public Trem() {
    this.velocidade = 60;
    this.passageiros = 0;
    this.posicaoAtual = 0;
}

}
