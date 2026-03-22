package utils;

import java.util.Random;

public class GerenciadorEstacoes {

    private int[][] dadosPassageiros;
    private int numeroEstacoes;

    public GerenciadorEstacoes(int numeroEstacoes) {
        this.numeroEstacoes = numeroEstacoes;
        this.dadosPassageiros = new int[numeroEstacoes][2];
    }

    public int[][] getDadosPassageiros() {
        return dadosPassageiros;
    }

    public void sortearPassageiros() {
        Random random = new Random();

        for (int i = 0; i < numeroEstacoes; i++) {
            int sobem, descem;

            do {
                sobem = random.nextInt(11);
                descem = random.nextInt(11);
            } while ((sobem + descem) % 2 != 0);

            validarNumero(sobem);
            validarNumero(descem);

            dadosPassageiros[i][0] = sobem;
            dadosPassageiros[i][1] = descem;

        }
    }

    private void validarNumero(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("Número não pode ser negativo");
        }
    }

    public void exibirDados() {
        for (int i = 0; i < numeroEstacoes; i++) {
            System.out.println("Estação " + i +
                    " | Sobem: " + dadosPassageiros[i][0] +
                    " | Descem: " + dadosPassageiros[i][1]);
        }
    }

}
