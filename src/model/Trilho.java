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
}