package utils;

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
}
