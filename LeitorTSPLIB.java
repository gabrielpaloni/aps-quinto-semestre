import java.io.File;
import java.util.Scanner;
import java.util.Locale;

public class LeitorTSPLIB {

    // Método estático que lê um arquivo e devolve um objeto Grafo preenchido
    public static Grafo lerArquivo(String caminhoArquivo) {
        try {
            File arquivo = new File(caminhoArquivo);
            Scanner scanner = new Scanner(arquivo);
            // Garante que o Java leia '.' como separador decimal, evitando crash por causa de vírgulas
            scanner.useLocale(Locale.US);

            // Variáveis temporárias para armazenar os dados lidos
            int dimensao = 0;
            String formatoPeso = "";
            double[][] coordenadas = null;
            Grafo grafo = null;

            // Varre o arquivo linha por linha
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine().trim();
                // Ignora linhas em branco
                if (linha.isEmpty()) continue;

                // Lê a quantidade total de cidades informada no cabeçalho do arquivo TSP
                if (linha.startsWith("DIMENSION")) {
                    // Pega o número que vem depois dos dois pontos ":"
                    int dimensaoReal = Integer.parseInt(linha.split(":")[1].trim());

                    // Independentemente do tamanho real do arquivo,
                    // limitamos a 14 para que o computador consiga terminar a Busca Exaustiva em tempo útil
                    // sem explodir a memória ou demorar semanas (já que O(n!) cresce muito rápido).
                    dimensao = 280;

                    // Cria o objeto grafo com a dimensão travada
                    grafo = new Grafo(dimensao);
                }
                // Lê qual é o formato em que os pesos estão estruturados no arquivo
                else if (linha.startsWith("EDGE_WEIGHT_FORMAT")) {
                    formatoPeso = linha.split(":")[1].trim();
                }
                // Caso o arquivo use coordenadas geográficas X e Y (Ex: a280.tsp)
                else if (linha.startsWith("NODE_COORD_SECTION")) {
                    coordenadas = new double[dimensao][2];
                    for (int i = 0; i < dimensao; i++) {
                        scanner.nextInt(); // Lê e descarta o ID da cidade
                        coordenadas[i][0] = scanner.nextDouble(); // Lê a coordenada X
                        coordenadas[i][1] = scanner.nextDouble(); // Lê a coordenada Y
                    }
                    // Usa o Teorema de Pitágoras para calcular a distância em linha reta
                    // entre todas as combinações de cidades e preenche a matriz de adjacência
                    for (int i = 0; i < dimensao; i++) {
                        for (int j = 0; j < dimensao; j++) {
                            double dX = coordenadas[i][0] - coordenadas[j][0];
                            double dY = coordenadas[i][1] - coordenadas[j][1];
                            grafo.adicionarAresta(i, j, Math.sqrt(dX * dX + dY * dY));
                        }
                    }
                    // Salva as coordenadas no grafo caso queira desenhar
                    grafo.setCoordenadas(coordenadas);
                }
                // Caso o arquivo não use X/Y, mas já forneça uma matriz de distâncias prontas (Ex: gr17.tsp)
                else if (linha.startsWith("EDGE_WEIGHT_SECTION")) {
                    // Lida com o formato de matriz triangular inferior
                    if (formatoPeso.equals("LOWER_DIAG_ROW")) {
                        for (int i = 0; i < dimensao; i++) {
                            for (int j = 0; j <= i; j++) {
                                double peso = scanner.nextDouble();
                                grafo.adicionarAresta(i, j, peso);
                            }
                        }
                    }
                    // Lida com formato de matriz quadrada completa
                    else {
                        for (int i = 0; i < dimensao; i++) {
                            for (int j = 0; j < dimensao; j++) {
                                grafo.adicionarAresta(i, j, scanner.nextDouble());
                            }
                        }
                    }
                }
            }
            scanner.close(); // Libera o recurso do arquivo
            return grafo;    // Devolve o grafo construído e preenchido para a Main

        } catch (Exception e) {
            // Captura qualquer erro de leitura ou parse e exibe no terminal
            System.out.println("Erro crítico na leitura do arquivo: " + e.getMessage());
            return null;
        }
    }
}