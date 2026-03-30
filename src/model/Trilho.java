package model;

public class Trilho {

    private NoTrilho inicio;

    public NoTrilho getInicio() {
        return inicio;
    }

    public void adicionarElemento(ElementoTrilho elemento) {
        NoTrilho novo = new NoTrilho(elemento);

        if (inicio == null) {
            inicio = novo;
        } else {
            NoTrilho atual = inicio;
            while (atual.getProximo() !=null) {
                atual = atual.getProximo();
                
            }
            atual.setProximo(novo);
        }
    }
    
}
