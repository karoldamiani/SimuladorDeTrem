package utils;

import model.Estacao;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class RelatorioUtils {
    
    public static void ordenarPorFluxo(List<Estacao> estacoes) {
        Collections.sort(estacoes, Comparator.comparingInt(Estacao::getFluxoTotal).reversed());
    }

    public static Estacao obterMaiorFluxo(List<Estacao> estacoes) {
        return estacoes.get(0);
    }
    
}
