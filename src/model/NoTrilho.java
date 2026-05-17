package model;

public class NoTrilho<T extends Elemento> {

    private T elemento;
    private NoTrilho<T> proximo;
    private NoTrilho<T> anterior;

    public NoTrilho(T elemento) {
        this.elemento = elemento;
    }

    public T getElemento() {
        return elemento;
    }

    public NoTrilho<T> getProximo() {
        return proximo;
    }

    public NoTrilho<T> getAnterior() {
        return anterior;
    }

    public void setProximo(NoTrilho<T> proximo) {
        this.proximo = proximo;
    }

    public void setAnterior(NoTrilho<T> anterior) {
        this.anterior = anterior;
    }

    
}