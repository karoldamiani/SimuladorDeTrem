package model;


public class Trilho {

    private NoTrilho<Elemento> inicio;
    private NoTrilho<Elemento> fim;

    public void adicionarElemento(Elemento elemento) {
        NoTrilho<Elemento> novo = new NoTrilho<>(elemento);

        if (inicio == null) {
            inicio = novo;
            fim = novo;
        } else {
            fim.setProximo(novo);
            novo.setAnterior(fim);
            fim = novo;
        }
    }

    public void imprimirIda() {
        NoTrilho<Elemento> atual = inicio;
        while (atual != null) {
            System.out.print(atual.getElemento().exibir());
            atual = atual.getProximo();
        }
        System.out.println();
    }

    public void imprimirVolta() {
        NoTrilho<Elemento> atual = fim;
        while (atual != null) {
            System.out.print(atual.getElemento().exibir());
            atual = atual.getAnterior();
        }
        System.out.println();
    }

    public NoTrilho<Elemento> getInicio() {
        return inicio;
    }

    public static Trilho construirTrilhoCompleto(int numeroEstacoes) {
        Trilho trilho = new Trilho();
        int posicao = 0;

        
        trilho.adicionarElemento(new Trecho(posicao++));

        for (int i = 1; i <= numeroEstacoes; i++) {
            
            for (int k = 0; k < 18; k++) {
                trilho.adicionarElemento(new Trecho(posicao++));
            }
            
            trilho.adicionarElemento(new Desvio(posicao++));
            
            trilho.adicionarElemento(new Estacao(posicao++, "Estacao " + i));
            
            trilho.adicionarElemento(new Desvio(posicao++));
        }

        
        for (int k = 0; k < 18; k++) {
            trilho.adicionarElemento(new Trecho(posicao++));
        }
        trilho.adicionarElemento(new Trecho(posicao)); // Ponto B

        return trilho;
    }

    public int getTamanho() {
        int count = 0;
        NoTrilho<Elemento> atual = inicio;
        while (atual != null) {
            count++;
            atual = atual.getProximo();
        }
        return count;
    }
}