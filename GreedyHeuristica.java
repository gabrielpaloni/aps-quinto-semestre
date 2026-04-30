import java.util.ArrayList;
import java.util.List;

public class GreedyHeuristica {

    // Método principal que recebe a estrutura do Grafo e o índice da cidade de partida
    public static void resolver(Grafo grafo, int cidadeInicial) {
        // Obtém a quantidade total de cidades no grafo
        int n = grafo.getNumeroVertices();

        // Array para rastrear quais cidades já foram visitadas (true) para não passarmos por elas de novo
        boolean[] visitados = new boolean[n];

        // Lista que armazenará a ordem das cidades visitadas para formar o trajeto final
        List<Integer> rota = new ArrayList<>();

        // Variável acumuladora para o custo total da viagem
        double custoTotal = 0.0;

        // Configuração inicial: começa pela cidade de origem
        int cidadeAtual = cidadeInicial;
        rota.add(cidadeAtual); // Adiciona a cidade de origem na primeira posição da rota
        visitados[cidadeAtual] = true; // Marca a cidade de origem como já visitada

        // Precisamos visitar (n - 1) cidades restantes
        for (int i = 0; i < n - 1; i++) {
            // Variáveis temporárias para encontrar o melhor próximo passo nesta iteração
            int proximaCidade = -1;
            // Inicializa a menor distância com o maior valor possível no Java,
            // garantindo que qualquer distância real encontrada seja menor que isso.
            double menorDistancia = Double.MAX_VALUE;

            // Procura o vizinho mais próximo não visitado
            for (int j = 0; j < n; j++) {
                // Condição para avaliar a cidade 'j':
                // 1. Ela não pode ter sido visitada ainda (!visitados[j])
                // 2. Tem que existir um caminho direto da cidade atual para 'j' (peso > 0)
                if (!visitados[j] && grafo.getPeso(cidadeAtual, j) > 0) {

                    // Pega o custo da cidade atual até a cidade 'j'
                    double distancia = grafo.getPeso(cidadeAtual, j);

                    // Se essa distância for menor que a menor que encontramos até agora neste loop
                    if (distancia < menorDistancia) {
                        //atualiza nossa "melhor opção atual"
                        menorDistancia = distancia;
                        proximaCidade = j;
                    }
                }
            }

            // Se encontrou uma próxima cidade válida (se o grafo for desconexo, proximaCidade poderia continuar -1)
            if (proximaCidade != -1) {
                visitados[proximaCidade] = true; // Marca a nova cidade como visitada
                rota.add(proximaCidade); // Adiciona a nova cidade ao nosso trajeto
                custoTotal += menorDistancia; // Soma a distância percorrida ao custo total
                cidadeAtual = proximaCidade; // A nova cidade passa a ser a "cidade atual" para o próximo loop
            }
        }

        // Retorna para a cidade inicial
        // Busca o peso da aresta que liga a última cidade visitada de volta à primeira
        double distanciaRetorno = grafo.getPeso(cidadeAtual, cidadeInicial);
        custoTotal += distanciaRetorno; // Adiciona esse último trecho ao custo total
        rota.add(cidadeInicial); // Adiciona a cidade inicial ao fim da lista para representar o ciclo fechado

        System.out.println("--- Resultado Heurística Gulosa (Greedy) ---");
        System.out.println("Melhor Rota: " + rota);
        System.out.println("Custo Total: " + custoTotal);
    }
}