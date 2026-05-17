package model;

public class Trilho {

    private NoTrilho<Elemento> inicio;
    private NoTrilho<Elemento> fim;

    public NoTrilho<Elemento> getInicio() {
        return inicio;
    }

    public NoTrilho<Elemento> getFim() {
        return fim;
    }

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

    public void imprimirIdaEVolta() {

        System.out.println("IDA:");
        NoTrilho<Elemento> atual = inicio;
        while (atual != null) {
            System.out.print("[ ]");
            atual = atual.getProximo();
        }

        System.out.println("\nVOLTA:");
        atual = fim;
        while (atual != null) {
            System.out.print("[ ]");
            atual = atual.getAnterior();
        }

        System.out.println();
    }
}