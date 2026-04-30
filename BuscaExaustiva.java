import java.util.ArrayList;
import java.util.List;

public class BuscaExaustiva {
    // Variável estática global  que guarda o menor custo encontrado até o momento
    private static double menorCusto = Double.MAX_VALUE;
    // Lista que guarda a sequência de cidades da melhor rota
    private static List<Integer> melhorRota = new ArrayList<>();

    // Método principal que dispara a busca exaustiva
    public static void resolver(Grafo grafo, int cidadeInicial) {
        int n = grafo.getNumeroVertices();
        // Array para controlar quais cidades já estão na rota atual
        boolean[] visitados = new boolean[n];
        // Rota que está sendo construída e avaliada no momento
        List<Integer> rotaAtual = new ArrayList<>();

        // Reseta as variáveis globais para o caso de o método ser chamado múltiplas vezes
        menorCusto = Double.MAX_VALUE;
        melhorRota.clear();

        // Configuração do ponto de partida
        visitados[cidadeInicial] = true;
        rotaAtual.add(cidadeInicial);

        // Inicia a recursão para explorar TODAS as combinações possíveis de rotas
        // Passa o custo inicial como 0.0
        buscar(grafo, cidadeInicial, cidadeInicial, visitados, rotaAtual, 0.0);

        // Imprime o resultado final após analisar todos os universos possíveis
        System.out.println("--- Resultado Busca Exaustiva ---");
        System.out.println("Melhor Rota: " + melhorRota);
        System.out.println("Custo Ótimo: " + menorCusto);
    }

    // Método recursivo que faz o trabalho pesado de explorar as rotas
    private static void buscar(Grafo grafo, int cidadeInicial, int cidadeAtual,
                               boolean[] visitados, List<Integer> rotaAtual, double custoAtual) {
        int n = grafo.getNumeroVertices();

        // Caso base da recursão: Se o tamanho da rota for igual ao total de cidades,
        // significa que terminamos de visitar todos, hora de voltar para a origem.
        if (rotaAtual.size() == n) {
            // Calcula o custo da última cidade de volta para a origem
            double custoRetorno = grafo.getPeso(cidadeAtual, cidadeInicial);
            // Soma o custo do trajeto todo com o custo de retorno
            double custoTotal = custoAtual + custoRetorno;

            // Se essa rota completa que acabamos de montar for mais barata que o nosso recorde atual
            if (custoTotal < menorCusto) {
                // temos um novo recorde que vira o menor custo salvando a rota
                menorCusto = custoTotal;
                melhorRota = new ArrayList<>(rotaAtual);
                melhorRota.add(cidadeInicial); // Adiciona a origem ao fim para fechar visualmente o ciclo
            }
            // Encerra esta ramificação da recursão
            return;
        }

        // Se ainda faltam cidades, tenta visitar cada cidade que ainda não foi marcada como visitada
        for (int i = 0; i < n; i++) {
            if (!visitados[i]) {
                // Faz o movimento: marca como visitada, adiciona na rota e pega o custo do trecho
                visitados[i] = true;
                rotaAtual.add(i);
                double custoAresta = grafo.getPeso(cidadeAtual, i);

                // Chama a si mesmo partindo dessa nova cidade, acumulando o custo
                buscar(grafo, cidadeInicial, i, visitados, rotaAtual, custoAtual + custoAresta);

                // BACKTRACKING: Ao voltar da recursão, precisamos "desfazer" o movimento.
                // Desmarcamos a cidade e a tiramos da lista para que o loop 'for' possa tentar
                // um caminho diferente na próxima iteração.
                visitados[i] = false;
                rotaAtual.remove(rotaAtual.size() - 1);
            }
        }
    }
}