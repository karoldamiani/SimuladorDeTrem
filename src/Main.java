import model.Estacao;
import model.NoTrilho;
import utils.GerenciadorEstacoes;
import java.util.ArrayList;
import java.util.List;
import utils.RelatorioUtils;
import model.Trem;
import model.Trilho;
import model.Desvio;
import model.Elemento;
import model.Trecho;

public class Main {

    public static void main(String[] args) throws Exception {

        Trilho trilho = new Trilho();

        trilho.adicionarElemento(new Trecho(0));
        trilho.adicionarElemento(new Trecho(1));
        trilho.adicionarElemento(new Estacao(2, "Estacao A"));
        trilho.adicionarElemento(new Trecho(3));
        trilho.adicionarElemento(new Trecho(4));
        trilho.adicionarElemento(new Estacao(5, "Estacao B"));
        trilho.adicionarElemento(new Trecho(6));
        trilho.adicionarElemento(new Trecho(7));
        trilho.adicionarElemento(new Trecho(8));
        trilho.adicionarElemento(new Trecho(9));

        System.out.println("IDA:");
        trilho.imprimirIda();

        System.out.println("VOLTA:");
        trilho.imprimirVolta();

        int numeroEstacoes = 2;
        GerenciadorEstacoes gerenciador = new GerenciadorEstacoes(numeroEstacoes);
        gerenciador.sortearPassageiros();
        int[][] dados = gerenciador.getDadosPassageiros();

        int index = 0;
        NoTrilho<Elemento> atual = trilho.getInicio();

        while (atual != null) {
            Elemento elemento = atual.getElemento();

            if (elemento.exibir().equals("[E]")) {
                Estacao estacao = (Estacao) elemento;

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

            atual = atual.getProximo();
        }

        List<Estacao> listaEstacoes = new ArrayList<>();

        atual = trilho.getInicio();
        while (atual != null) {
            if (atual.getElemento() instanceof Estacao) {
                listaEstacoes.add((Estacao) atual.getElemento());
            }
            atual = atual.getProximo();
        }

        RelatorioUtils.ordenarPorFluxo(listaEstacoes);

        System.out.println("\n FLUXO DE TODAS AS ESTACOES ");
        for (Estacao e : listaEstacoes) {
            System.out.println("Posicao " + e.getPosicao() +
                    " | Fluxo: " + e.getFluxoTotal());
        }

        Estacao maior = RelatorioUtils.obterMaiorFluxo(listaEstacoes);

        System.out.println("\n ESTACAO COM MAIOR FLUXO ");
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