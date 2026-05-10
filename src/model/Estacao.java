package model;

import utils.StaticQueue;

public class Estacao extends ElementoTrilho {
    private String nome;
    private int passageirosPresentes = 0;

    private int totalPassageirosSubiram = 0;
    private int totalPassageirosDesceram = 0;

    private int pessoasSubindo;
    private int pessoasDescendo;

    public Estacao(int posicao, String nome) {
        super(posicao);
        this.nome = nome;
    }

    @Override
    public void exibir() {
        System.out.println("Estação: " + nome + " na posição " + posicao);
    }

    public void embarcar(int qtd) {
        if (qtd < 0) {
            throw new IllegalArgumentException("Valor Inválido");
        }
        passageirosPresentes += qtd;
        pessoasSubindo = qtd;
    }

    public void desembarcar(int qtd) {
        if (qtd < 0 || qtd > passageirosPresentes) {
            throw new IllegalArgumentException("Erro no desembarque");
        }
        passageirosPresentes -= qtd;
        pessoasDescendo = qtd;
    }

    public int getPassageirosPresentes() {
        return passageirosPresentes;
    }

    public void setPessoasSubindo(int pessoasSubindo) {
        this.pessoasSubindo = pessoasSubindo;
    }

    public void setPessoasDescendo(int pessoasDescendo) {
        this.pessoasDescendo = pessoasDescendo;
    }

    public int getPessoasSubindo() {
        return pessoasSubindo;
    }

    public int getPessoasDescendo() {
        return pessoasDescendo;
    }

    public void registrarMovimento() {
        totalPassageirosSubiram += pessoasSubindo;
        totalPassageirosDesceram += pessoasDescendo;

        pessoasSubindo = 0;
        pessoasDescendo = 0;
    }

    public int getTotalPassageirosSubiram() {
        return totalPassageirosSubiram;
    }

    public int getTotalPassageirosDesceram() {
        return totalPassageirosDesceram;
    }

    public int getFluxoTotal() {
        return totalPassageirosSubiram + totalPassageirosDesceram;
    }

    
    private StaticQueue<Trem> fila = new StaticQueue<>(10);

    public void receberTrem(Trem trem) throws Exception {
        System.out.println("Trem aguardando na ESTAÇÃO...");
        fila.enqueue(trem);
    }

    public Trem liberarTrem() throws Exception {
        System.out.println("Trem saindo da ESTAÇÃO...");
        return fila.dequeue();
    }

}
