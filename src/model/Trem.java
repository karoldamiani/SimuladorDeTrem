package model;

public class Trem {

    public enum Estado { EM_MOVIMENTO, PARADO_ESTACAO, EM_DESVIO }

    private String nome;
    private int velocidade;
    private int passageiros;
    private int posicaoAtual;
    private Estado estado;
    private boolean direcaoAB; // true = A→B, false = B→A
    private int minutosParado; // controla tempo de parada

    public Trem(String nome, int passageiros, boolean direcaoAB, int posicaoInicial) {
        this.nome = nome;
        this.velocidade = 1; // 1 km/min
        this.passageiros = passageiros;
        this.posicaoAtual = posicaoInicial;
        this.estado = Estado.EM_MOVIMENTO;
        this.direcaoAB = direcaoAB;
        this.minutosParado = 0;
    }

    
    public Trem(String nome) {
        this(nome, 0, true, 0);
    }

    public Trem() {
        this("Trem", 0, true, 0);
    }

    public Trem(int velocidade, int passageiros, int posicaoInicial) {
        this("Trem", passageiros, true, posicaoInicial);
        this.velocidade = velocidade;
    }

    public void mover() {
        if (estado == Estado.EM_MOVIMENTO) {
            if (direcaoAB) posicaoAtual++;
            else posicaoAtual--;
        }
    }

    public void embarcar(int quantidade) {
        passageiros += quantidade;
    }

    public void desembarcar(int quantidade) {
        if (quantidade > passageiros) quantidade = passageiros;
        passageiros -= quantidade;
    }

    public int calcularTempoTotal(NoTrilho<Elemento> noAtual) {
        if (noAtual == null) return 0;
        int tempoNoAtual = 1;
        if (noAtual.getElemento() instanceof Estacao) {
            Estacao estacao = (Estacao) noAtual.getElemento();
            int pessoas = estacao.getPessoasSubindo() + estacao.getPessoasDescendo();
            tempoNoAtual += pessoas == 0 ? 1 : (pessoas * 30) / 60;
        }
        return tempoNoAtual + calcularTempoTotal(noAtual.getProximo());
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public int getVelocidade() { return velocidade; }
    public int getPassageiros() { return passageiros; }
    public int getPosicaoAtual() { return posicaoAtual; }
    public Estado getEstado() { return estado; }
    public boolean isDirecaoAB() { return direcaoAB; }
    public int getMinutosParado() { return minutosParado; }

    public void setEstado(Estado estado) { this.estado = estado; }
    public void setPosicaoAtual(int posicaoAtual) { this.posicaoAtual = posicaoAtual; }
    public void setMinutosParado(int minutosParado) { this.minutosParado = minutosParado; }

    @Override
    public String toString() {
        return nome;
    }
}