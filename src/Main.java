import model.Estacao;
import model.Trem;
import model.Trilho;
import model.ElementoTrilho;
import utils.GerenciadorEstacoes;

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

        System.out.println("\n");

        int numeroEstacoes = 2;

        GerenciadorEstacoes gerenciador = new GerenciadorEstacoes(numeroEstacoes);
        gerenciador.sortearPassageiros();

        int[][] dados = gerenciador.getDadosPassageiros();

        int index = 0;

        for (int i = 0; i < trilho.length; i++) {
            if (trilho[i] instanceof Estacao) {
                Estacao estacao = (Estacao) trilho[i];

                try {
                    estacao.embarcar(dados[index][0]);
                    estacao.desembarcar(dados[index][1]);

                    System.out.println(estacao.getPosicao() +
                            " | Presentes: " + estacao.getPassageirosPresentes());

                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }

                index++;
            }
        }   
        

    }

}