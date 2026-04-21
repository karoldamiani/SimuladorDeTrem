package model;

public class Trilho {

    private NoTrilho<Elemento> inicio;

    public NoTrilho<Elemento> getInicio() {
        return inicio;
    }

    public void adicionarElemento(Elemento elemento) {
        NoTrilho<Elemento> novo = new NoTrilho<>(elemento);

        if (inicio == null) {
            inicio = novo;
        } else {
            NoTrilho<Elemento> atual = inicio;
            while (atual.getProximo() != null) {
                atual = atual.getProximo();
            }
            atual.setProximo(novo);
        }
    }
}