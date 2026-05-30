package view;

import model.Estacao;
import model.Trem;

import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private Scanner scanner = new Scanner(System.in);

    public void exibirEstadoSimulacao(int minutoAtual, List<Trem> trens, List<Estacao> estacoes) {
        int horas = 8 + minutoAtual / 60;
        int minutos = minutoAtual % 60;

        System.out.println("\n==================== SIMULADOR DE TREM ====================");
        System.out.printf("Tempo: %02dh%02dmin%n", horas, minutos);

        System.out.println("\nTrens em Operação:");
        for (Trem t : trens) {
            System.out.println("  - " + t.getNome()
                    + " | Posição: " + t.getPosicaoAtual() + " km"
                    + " | Passageiros: " + t.getPassageiros()
                    + " | Estado: " + t.getEstado()
                    + " | Direção: " + (t.isDirecaoAB() ? "A→B" : "B→A"));
        }

        System.out.println("\nEstado das Estações:");
        for (Estacao e : estacoes) {
            System.out.println("  - " + e
                    + " | Subiram(total): " + e.getTotalPassageirosSubiram()
                    + " | Desceram(total): " + e.getTotalPassageirosDesceram());
        }

        System.out.println("\nPressione ENTER para avançar para o próximo minuto...");
        scanner.nextLine();
    }

    public void exibirMensagem(String mensagem) {
        System.out.println("[INFO] " + mensagem);
    }

    public void exibirRelatorioFinal(List<Estacao> estacoes) {
        System.out.println("\n==================== RELATÓRIO FINAL ====================");
        for (Estacao e : estacoes) {
            System.out.println(e + " | Subiram: " + e.getTotalPassageirosSubiram()
                    + " | Desceram: " + e.getTotalPassageirosDesceram());
        }
    }
}