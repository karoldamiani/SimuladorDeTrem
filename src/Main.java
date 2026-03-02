import model.Estacao;
import model.ElementoTrilho;

public class Main {

    public static void main(String[] args) {

        ElementoTrilho[] trilho = new ElementoTrilho[10];

        trilho[2] = new Estacao(2, "Estacao A");
        trilho[5] = new Estacao(5, "Estacao B");

        for (int i = 0; i < trilho.length; i++) {
            if (trilho[i] instanceof Estacao) {
                System.out.print("[E]");
            } else {
                System.out.print("[ ]");
            }
        }
    }
}