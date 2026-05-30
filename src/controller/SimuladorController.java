package controller;

import model.*;
import utils.GerenciadorEstacoes;
import utils.RelatorioUtils;
import view.ConsoleView;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimuladorController {

    private Trilho trilho;
    private List<Trem> trens;
    private List<Estacao> estacoes;
    private ConsoleView view;
    private int numeroEstacoes;
    private int tamanhoTrilho;
    private List<NoTrilho<Elemento>> nos; // lista dos nós para acesso por índice

    private static final int FIM_SAIDA_MIN = 570;

    public SimuladorController() {
        this.view = new ConsoleView();
        this.trens = new ArrayList<>();
        this.estacoes = new ArrayList<>();
        this.nos = new ArrayList<>();
    }

    public void iniciarSimulacao() {
        Random random = new Random();
        numeroEstacoes = random.nextInt(21) + 10;
        view.exibirMensagem("Numero de estacoes sorteado: " + numeroEstacoes);

        trilho = Trilho.construirTrilhoCompleto(numeroEstacoes);
        tamanhoTrilho = trilho.getTamanho();
        view.exibirMensagem("Trilho montado com " + tamanhoTrilho + " nos.");

        // Indexar os nós para acesso rápido por posição
        NoTrilho<Elemento> atual = trilho.getInicio();
        while (atual != null) {
            nos.add(atual);
            if (atual.getElemento() instanceof Estacao) {
                estacoes.add((Estacao) atual.getElemento());
            }
            atual = atual.getProximo();
        }

        // Sortear passageiros iniciais
        GerenciadorEstacoes gerenciador = new GerenciadorEstacoes(numeroEstacoes);
        gerenciador.sortearPassageiros();
        int[][] dados = gerenciador.getDadosPassageiros();
        for (int i = 0; i < estacoes.size(); i++) {
            estacoes.get(i).setPessoasSubindo(dados[i][0]);
            estacoes.get(i).setPessoasDescendo(dados[i][1]);
        }

        // Loop de simulação
        for (int minuto = 0; minuto <= FIM_SAIDA_MIN + tamanhoTrilho; minuto++) {

            if (minuto <= FIM_SAIDA_MIN && minuto % 30 == 0) {
                lancarTrens(minuto, random);
            }

            avancarTrens(random);

            trens.removeIf(t -> t.getPosicaoAtual() < 0 || t.getPosicaoAtual() >= tamanhoTrilho);

            view.exibirEstadoSimulacao(minuto, trens, estacoes);

            if (minuto > FIM_SAIDA_MIN && trens.isEmpty()) break;
        }

        gerarRelatorioFinal();
    }

    private void lancarTrens(int minuto, Random random) {
        int passageirosAB = random.nextInt(41) + 10;
        int passageirosBA = random.nextInt(41) + 10;

        String nomeAB = "Trem-AB-" + (minuto / 30 + 1);
        String nomeBA = "Trem-BA-" + (minuto / 30 + 1);

        trens.add(new Trem(nomeAB, passageirosAB, true, 0));
        trens.add(new Trem(nomeBA, passageirosBA, false, tamanhoTrilho - 1));

        view.exibirMensagem("Saindo: " + nomeAB + " (A->B, " + passageirosAB + " passageiros) e "
                + nomeBA + " (B->A, " + passageirosBA + " passageiros)");
    }

    private void avancarTrens(Random random) {
        for (Trem trem : trens) {

            // Se parado, decrementa tempo e continua
            if (trem.getEstado() != Trem.Estado.EM_MOVIMENTO) {
                trem.setMinutosParado(trem.getMinutosParado() - 1);
                if (trem.getMinutosParado() <= 0) {
                    trem.setEstado(Trem.Estado.EM_MOVIMENTO);
                }
                continue;
            }

            // Move o trem
            trem.mover();

            int pos = trem.getPosicaoAtual();
            if (pos < 0 || pos >= tamanhoTrilho) continue;

            // Pega o elemento na posição atual usando a lista indexada
            Elemento elemento = nos.get(pos).getElemento();

            if (elemento instanceof Estacao) {
                processarEstacao(trem, (Estacao) elemento, random);
            } else if (elemento instanceof Desvio) {
                processarDesvio(trem);
            }
        }
    }

    private void processarEstacao(Trem trem, Estacao estacao, Random random) {
        int descem = Math.min(estacao.getPessoasDescendo(), trem.getPassageiros());
        int sobem = estacao.getPessoasSubindo();

        trem.desembarcar(descem);
        trem.embarcar(sobem);
        estacao.registrarMovimento();

        // Ressortear para próxima passagem
        int novosSobem, novosDescem;
        do {
            novosSobem = random.nextInt(11);
            novosDescem = random.nextInt(novosSobem + 1);
        } while ((novosSobem + novosDescem) % 2 != 0);

        estacao.setPessoasSubindo(novosSobem);
        estacao.setPessoasDescendo(novosDescem);

        int totalPessoas = descem + sobem;
        int tempoParada = totalPessoas == 0 ? 1 : Math.max(1, (totalPessoas * 30) / 60);

        trem.setEstado(Trem.Estado.PARADO_ESTACAO);
        trem.setMinutosParado(tempoParada);

        view.exibirMensagem(trem.getNome() + " parou em " + estacao
                + " | Desceram: " + descem + " | Embarcaram: " + sobem
                + " | Parada: " + tempoParada + " min");
    }

    private void processarDesvio(Trem trem) {
        for (Trem outro : trens) {
            if (outro == trem) continue;
            if (outro.isDirecaoAB() != trem.isDirecaoAB()) {
                int distancia = Math.abs(trem.getPosicaoAtual() - outro.getPosicaoAtual());
                if (distancia <= 3) {
                    trem.setEstado(Trem.Estado.EM_DESVIO);
                    trem.setMinutosParado(2);
                    view.exibirMensagem(trem.getNome() + " aguardando no desvio (pos "
                            + trem.getPosicaoAtual() + ")");
                    break;
                }
            }
        }
    }

    public void gerarRelatorioFinal() {
        RelatorioUtils.ordenarPorFluxo(estacoes);
        view.exibirRelatorioFinal(estacoes);

        try (FileWriter fw = new FileWriter("relatorio_final.txt")) {
            fw.write("RELATORIO FINAL DE PASSAGEIROS\n");
            fw.write("================================\n");
            for (Estacao e : estacoes) {
                fw.write(e + " | Subiram: " + e.getTotalPassageirosSubiram()
                        + " | Desceram: " + e.getTotalPassageirosDesceram()
                        + " | Fluxo Total: " + e.getFluxoTotal() + "\n");
            }
            view.exibirMensagem("Arquivo relatorio_final.txt gerado!");
        } catch (IOException e) {
            view.exibirMensagem("Erro ao gerar arquivo: " + e.getMessage());
        }
    }
}