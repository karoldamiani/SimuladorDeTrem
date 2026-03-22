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

    private int passageirosPresentes = 0;

    public void embarcar(int qtd) {
        if (qtd < 0) {
            throw new IllegalArgumentException("Valor Inválido");
        }
        passageirosPresentes += qtd;
    }

    public void desembarcar(int qtd) {
        if (qtd < 0 || qtd > passageirosPresentes) {
        throw new IllegalArgumentException("Erro no desembarque");
    }
    passageirosPresentes -= qtd;
    }

    public int getPassageirosPresentes() {
        return passageirosPresentes;
    }
}
