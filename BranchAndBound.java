import java.util.ArrayList;
import java.util.List;

public class BranchAndBound {
    // Variáveis globais para rastrear o melhor caminho, assim como na Busca Exaustiva
    private static double menorCusto = Double.MAX_VALUE;
    private static List<Integer> melhorRota = new ArrayList<>();

    // O método de inicialização é idêntico ao da Busca Exaustiva
    public static void resolver(Grafo grafo, int cidadeInicial) {
        int n = grafo.getNumeroVertices();
        boolean[] visitados = new boolean[n];
        List<Integer> rotaAtual = new ArrayList<>();

        menorCusto = Double.MAX_VALUE;
        melhorRota.clear();

        visitados[cidadeInicial] = true;
        rotaAtual.add(cidadeInicial);

        // Dispara a recursão com poda
        buscar(grafo, cidadeInicial, cidadeInicial, visitados, rotaAtual, 0.0);

        System.out.println("--- Resultado Branch and Bound ---");
        System.out.println("Melhor Rota: " + melhorRota);
        System.out.println("Custo Ótimo: " + menorCusto);
    }

    private static void buscar(Grafo grafo, int cidadeInicial, int cidadeAtual,
                               boolean[] visitados, List<Integer> rotaAtual, double custoAtual) {

        // Antes de continuar, ele verifica se o custo que acumulou até o momento é maior ou igual ao menorCusto do momento de uma rota completa.
        // Se sim, não faz sentido continuar montando essa rota.
        if (custoAtual >= menorCusto) {
            return; // Interrompe a recursão prematuramente, economizando muito tempo de processamento
        }

        int n = grafo.getNumeroVertices();

        // Daqui para baixo, o comportamento é idêntico ao da Busca Exaustiva
        // Se chegou até o fim e o custo for melhor, atualiza o recorde
        if (rotaAtual.size() == n) {
            double custoRetorno = grafo.getPeso(cidadeAtual, cidadeInicial);
            double custoTotal = custoAtual + custoRetorno;

            if (custoTotal < menorCusto) {
                menorCusto = custoTotal;
                melhorRota = new ArrayList<>(rotaAtual);
                melhorRota.add(cidadeInicial);
            }
            return;
        }

        // Loop de ramificação e Backtracking
        for (int i = 0; i < n; i++) {
            if (!visitados[i]) {
                visitados[i] = true;
                rotaAtual.add(i);
                double custoAresta = grafo.getPeso(cidadeAtual, i);

                buscar(grafo, cidadeInicial, i, visitados, rotaAtual, custoAtual + custoAresta);

                // Backtracking para tentar a próxima ramificação
                visitados[i] = false;
                rotaAtual.remove(rotaAtual.size() - 1);
            }
        }
    }
}