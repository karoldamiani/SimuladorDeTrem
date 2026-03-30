package model;

public class NoTrilho {

    private ElementoTrilho elemento;
    private NoTrilho proximo;

    public NoTrilho(ElementoTrilho elemento) {
        this.elemento = elemento;
        this.proximo = null;
    }
    
    public ElementoTrilho getElemento() {
        return elemento;
    }

    public NoTrilho getProximo() {
        return proximo;
    }

    public void setProximo(NoTrilho proximo) {
        this.proximo = proximo;
    }
}
