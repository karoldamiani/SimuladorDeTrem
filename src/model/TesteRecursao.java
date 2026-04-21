// package model;

// // Classe de teste usada na Semana 5–6 para validar
// public class TesteRecursao {

//     public static void main(String[] args) {

//         // trilho encadeado
//         Trilho trilho = new Trilho();

//         // trecho comum (1 km)
//         trilho.adicionarElemento(new ElementoTrilho(0) {
//             public void exibir() {}
//         });

//         // estação com pessoas
//         Estacao e1 = new Estacao(1, "Estacao Teste");
//         e1.setPessoasSubindo(4);
//         e1.setPessoasDescendo(2);
//         trilho.adicionarElemento(e1);

//         // mais trechos
//         trilho.adicionarElemento(new ElementoTrilho(2) {
//             public void exibir() {}
//         });

//         trilho.adicionarElemento(new ElementoTrilho(3) {
//             public void exibir() {}
//         });

//         // Criar trem
//         Trem trem = new Trem();

//         // Calcular tempo total
//         int tempo = trem.calcularTempoTotal(trilho.getInicio());

//         System.out.println("Tempo total calculado: " + tempo + " minutos");
//     }
    
// }
