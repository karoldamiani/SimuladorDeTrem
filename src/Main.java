import model.Estacao;
import model.Trem;
import model.Trilho;
import model.ElementoTrilho;
import utils.GerenciadorEstacoes;
import java.util.ArrayList;
import java.util.List;
import utils.RelatorioUtils;

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

        List<Estacao> listaEstacoes = new ArrayList<>();

        for (ElementoTrilho e : trilho) {
            if (e instanceof Estacao) {
                listaEstacoes.add((Estacao) e);
            }
        }

        RelatorioUtils.ordenarPorFluxo(listaEstacoes);

        Estacao maior = RelatorioUtils.obterMaiorFluxo(listaEstacoes);

        System.out.println("\n=== ESTAÇÃO COM MAIOR FLUXO ===");
        System.out.println("Posição: " + maior.getPosicao());
        System.out.println("Fluxo Total: " + maior.getFluxoTotal());

    }

}