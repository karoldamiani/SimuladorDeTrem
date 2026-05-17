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
}