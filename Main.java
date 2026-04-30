public class Main {
    public static void main(String[] args) {
        // Caminho do arquivo de dados TSP no computador.
        String caminhoArquivo = "/home/flow/Documentos/APS_Quinto_Semestre/a280.tsp";

        System.out.println("Lendo TSPLIB...");
        Grafo grafo = LeitorTSPLIB.lerArquivo(caminhoArquivo);

        // Se o arquivo foi lido com sucesso e o grafo existe
        if (grafo != null) {
            int n = grafo.getNumeroVertices();
            System.out.println("Grafo carregado! Testando com " + n + " cidades.");

            // ========================================================================
            // TESTE 1: SENSIBILIDADE DA HEURÍSTICA GREEDY
            // ========================================================================
            System.out.println("\n--- INICIANDO TESTE DE SENSIBILIDADE (GREEDY) ---");
            // Array definindo de quais cidades vamos tentar partir
            int[] pontosDePartida = {0};

            for (int inicio : pontosDePartida) {
                // Segurança: só executa se a cidade de início existir no grafo cortado
                if (inicio < n) {
                    System.out.println("Executando Greedy partindo da cidade: " + inicio);
                    // Marca o relógio antes de iniciar a execução
                    long tempoInicio = System.currentTimeMillis();

                    // Chama o algoritmo
                    GreedyHeuristica.resolver(grafo, inicio);

                    // Marca o relógio após a execução
                    long tempoFim = System.currentTimeMillis();

                    // Imprime o tempo gasto subtraindo o início do fim
                    System.out.println("Tempo de processamento: " + (tempoFim - tempoInicio) + " ms");
                    System.out.println("-------------------------------------------");
                }
            }

            // ========================================================================
            // TESTE 2: COMPARAÇÃO DE DESEMPENHO (ÓTIMO VS HEURÍSTICO)
            // ========================================================================

            // Trava de segurança no próprio Main. Algoritmos O(n!) travam computadores acima de 15 cidades.
            if (n <= 15) {
                System.out.println("\n--- INICIANDO COMPARAÇÃO ÓTIMA (ESTRESSE) ---");

                // Teste do Branch and Bound
                System.out.println("\n1. Executando Branch and Bound...");
                long inicioBB = System.currentTimeMillis();
                BranchAndBound.resolver(grafo, 0); // Sempre retorna a rota perfeita
                long fimBB = System.currentTimeMillis();
                System.out.println("Tempo Total B&B: " + (fimBB - inicioBB) + " ms");

                // Teste da Busca Exaustiva Pura
                System.out.println("\n2. Executando Busca Exaustiva...");
                long inicioE = System.currentTimeMillis();
                BuscaExaustiva.resolver(grafo, 0); // Sempre retorna a rota perfeita, mas explora rotas inúteis
                long fimE = System.currentTimeMillis();
                System.out.println("Tempo Total Exaustiva: " + (fimE - inicioE) + " ms");
            } else {
                // Se a trava do Leitor for removida e 'n' for grande (ex: 280), o código barra a execução
                System.out.println("\n[AVISO] Testes de Solução Ótima ignorados.");
                System.out.println("Motivo: Instância com " + n + " cidades causaria explosão combinatória O(n!).");
            }
        }
    }
}