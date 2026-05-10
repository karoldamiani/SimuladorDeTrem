import model.Estacao;
import model.ElementoTrilho;
import utils.GerenciadorEstacoes;
import java.util.ArrayList;
import java.util.List;
import utils.RelatorioUtils;
import model.Trem;
import model.Desvio;

public class Main {

    public static void main(String[] args) throws Exception {

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

                    estacao.registrarMovimento();

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

        System.out.println("\n FLUXO DE TODAS AS ESTACOES ");
        for (Estacao e : listaEstacoes) {
            System.out.println("Posicao " + e.getPosicao() +
                    " | Fluxo: " + e.getFluxoTotal());
        }

        Estacao maior = RelatorioUtils.obterMaiorFluxo(listaEstacoes);

        System.out.println("\n ESTACAOO COM MAIOR FLUXO ");
        System.out.println("Posicao: " + maior.getPosicao());
        System.out.println("Fluxo Total: " + maior.getFluxoTotal());

        System.out.println("\nTESTE PILHA (DESVIO)");

        Desvio desvio = new Desvio();

        Trem t1 = new Trem("Trem A");
        Trem t2 = new Trem("Trem B");
        Trem t3 = new Trem("Trem C");

        desvio.receberTrem(t1);
        desvio.receberTrem(t2);
        desvio.receberTrem(t3);

        System.out.println(desvio.liberarTrem());
        System.out.println(desvio.liberarTrem());
        System.out.println(desvio.liberarTrem());

        System.out.println("\nTESTE FILA (ESTACAO) ");

        Estacao estacaoTeste = new Estacao(99, "Estacao Teste");

        estacaoTeste.receberTrem(t1);
        estacaoTeste.receberTrem(t2);
        estacaoTeste.receberTrem(t3);

        System.out.println(estacaoTeste.liberarTrem());
        System.out.println(estacaoTeste.liberarTrem());
        System.out.println(estacaoTeste.liberarTrem());

    }

}