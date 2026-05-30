# Simulador de Trem - Programação II

Simulação de trens transitando entre os pontos A e B em uma cidade do interior.

## Funcionalidades
- Trilho encadeado com 20 nós entre estações e nós de desvio
- N estações sorteadas entre 10 e 30
- Trens saindo a cada 30 minutos das 8h às 17h30
- Simulação minuto a minuto (pressionar Enter para avançar)
- Embarque/desembarque com 30 segundos por pessoa
- Lógica de desvio para evitar colisões
- Relatório final gerado em arquivo .txt

## Estrutura
- `model/` — Trem, Trilho, Estacao, Desvio, NoTrilho, ElementoTrilho
- `view/` — ConsoleView
- `controller/` — SimuladorController
- `utils/` — StaticStack, StaticQueue, GerenciadorEstacoes, RelatorioUtils