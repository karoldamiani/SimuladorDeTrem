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

    private static final int INICIO_MIN = 0;
    private static final int FIM_SAIDA_MIN = 570;

     public SimuladorController() {
        this.view = new ConsoleView();
        this.trens = new ArrayList<>();
        this.estacoes = new ArrayList<>();
    }

    public void iniciarSimulacao() {
        
        Random random = new Random();
        numeroEstacoes = random.nextInt(21) + 10; 
        view.exibirMensagem("Número de estações sorteado: " + numeroEstacoes);

        
        trilho = Trilho.construirTrilhoCompleto(numeroEstacoes);
        tamanhoTrilho = trilho.getTamanho();
        view.exibirMensagem("Trilho montado com " + tamanhoTrilho + " nós.");

        
        NoTrilho<Elemento> atual = trilho.getInicio();
        while (atual != null) {
            if (atual.getElemento() instanceof Estacao) {
                estacoes.add((Estacao) atual.getElemento());
            }
            atual = atual.getProximo();
        }

        
        GerenciadorEstacoes gerenciador = new GerenciadorEstacoes(numeroEstacoes);
        gerenciador.sortearPassageiros();
        int[][] dados = gerenciador.getDadosPassageiros();
        for (int i = 0; i < estacoes.size(); i++) {
            estacoes.get(i).setPessoasSubindo(dados[i][0]);
            estacoes.get(i).setPessoasDescendo(dados[i][1]);
        }

        
        for (int minuto = 0; minuto <= FIM_SAIDA_MIN + tamanhoTrilho; minuto++) {

            
            if (minuto <= FIM_SAIDA_MIN && minuto % 30 == 0) {
                lancarTrens(minuto, random);
            }

            
            avancarTrens(minuto, gerenciador);

            
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

        Trem tremAB = new Trem(nomeAB, passageirosAB, true, 0);
        Trem tremBA = new Trem(nomeBA, passageirosBA, false, tamanhoTrilho - 1);

        trens.add(tremAB);
        trens.add(tremBA);

        view.exibirMensagem("Saindo: " + nomeAB + " (A→B, " + passageirosAB + " passageiros) e "
                + nomeBA + " (B→A, " + passageirosBA + " passageiros)");
    }

    private void avancarTrens(int minuto, GerenciadorEstacoes gerenciador) {
        for (Trem trem : trens) {
            if (trem.getEstado() == Trem.Estado.PARADO_ESTACAO
                    || trem.getEstado() == Trem.Estado.EM_DESVIO) {
                int restante = trem.getMinutosParado() - 1;
                trem.setMinutosParado(restante);
                if (restante <= 0) {
                    trem.setEstado(Trem.Estado.EM_MOVIMENTO);
                }
                continue;
            }

            trem.mover();

            int pos = trem.getPosicaoAtual();
            if (pos < 0 || pos >= tamanhoTrilho) continue;

            
            Elemento elemento = getElementoNaPosicao(pos);

            if (elemento instanceof Estacao) {
                Estacao estacao = (Estacao) elemento;
                processarEstacao(trem, estacao, gerenciador);
            } else if (elemento instanceof Desvio) {
                processarDesvio(trem, (Desvio) elemento);
            }
        }
    }

    private void processarEstacao(Trem trem, Estacao estacao, GerenciadorEstacoes gerenciador) {
        int descem = estacao.getPessoasDescendo();
        int sobem = estacao.getPessoasSubindo();

        trem.desembarcar(descem);
        trem.embarcar(sobem);
        estacao.registrarMovimento();

        
        Random r = new Random();
        int novosSobem, novosDescem;
        do {
            novosSobem = r.nextInt(11);
            novosDescem = r.nextInt(novosSobem + 1);
        } while ((novosSobem + novosDescem) % 2 != 0);

        estacao.setPessoasSubindo(novosSobem);
        estacao.setPessoasDescendo(novosDescem);

        int totalPessoas = descem + sobem;
        int tempoParada = totalPessoas == 0 ? 1 : (totalPessoas * 30) / 60;
        if (tempoParada < 1) tempoParada = 1;

        trem.setEstado(Trem.Estado.PARADO_ESTACAO);
        trem.setMinutosParado(tempoParada);

        view.exibirMensagem(trem.getNome() + " parou em " + estacao
                + " | Desembarcaram: " + descem + " | Embarcaram: " + sobem
                + " | Tempo de parada: " + tempoParada + " min");
    }

    private void processarDesvio(Trem trem, Desvio desvio) {
        
        boolean colisaoDetectada = false;
        for (Trem outro : trens) {
            if (outro == trem) continue;
            if (outro.isDirecaoAB() != trem.isDirecaoAB()) {
                int distancia = Math.abs(trem.getPosicaoAtual() - outro.getPosicaoAtual());
                if (distancia <= 3) {
                    colisaoDetectada = true;
                    break;
                }
            }
        }

        if (colisaoDetectada) {
            trem.setEstado(Trem.Estado.EM_DESVIO);
            trem.setMinutosParado(2);
            view.exibirMensagem(trem.getNome() + " aguardando no desvio (pos " + trem.getPosicaoAtual() + ")");
        }
    }

    private Elemento getElementoNaPosicao(int posicao) {
        NoTrilho<Elemento> atual = trilho.getInicio();
        int i = 0;
        while (atual != null) {
            if (i == posicao) return atual.getElemento();
            i++;
            atual = atual.getProximo();
        }
        return null;
    }

    public void gerarRelatorioFinal() {
        RelatorioUtils.ordenarPorFluxo(estacoes);
        view.exibirRelatorioFinal(estacoes);

        // Gera arquivo .txt
        try (FileWriter fw = new FileWriter("relatorio_final.txt")) {
            fw.write("RELATÓRIO FINAL DE PASSAGEIROS\n");
            fw.write("================================\n");
            for (Estacao e : estacoes) {
                fw.write(e + " | Subiram: " + e.getTotalPassageirosSubiram()
                        + " | Desceram: " + e.getTotalPassageirosDesceram()
                        + " | Fluxo Total: " + e.getFluxoTotal() + "\n");
            }
            view.exibirMensagem("Arquivo 'relatorio_final.txt' gerado com sucesso!");
        } catch (IOException e) {
            view.exibirMensagem("Erro ao gerar arquivo: " + e.getMessage());
        }
    }
}
