package model;

public class NoTrilho<T extends Elemento> {

    private T elemento;
    private NoTrilho<T> proximo;

    public NoTrilho(T elemento) {
        this.elemento = elemento;
    }

    public T getElemento() {
        return elemento;
    }

    public NoTrilho<T> getProximo() {
        return proximo;
    }

    public void setProximo(NoTrilho<T> proximo) {
        this.proximo = proximo;
    }
}