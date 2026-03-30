package model;

public class Estacao extends ElementoTrilho {
    private String nome;
    private int passageirosPresentes = 0;

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

}
