public class Grafo {
    // Quantidade total de cidades no nosso problema
    private int numeroVertices;

    // Matriz quadrada que guarda a distância entre a cidade da linha 'i' e a cidade da coluna 'j'
    private double[][] matrizAdjacencia;

    // Matriz para guardar a posição de cada cidade no espaço
    private double[][] coordenadas;

    // Construtor: Prepara o terreno quando um novo Grafo é instanciado
    public Grafo(int numeroVertices) {
        this.numeroVertices = numeroVertices;
        // Inicializa a matriz de distâncias com zeros. O tamanho é [n][n]
        this.matrizAdjacencia = new double[numeroVertices][numeroVertices];
    }

    // Define o peso entre duas cidades de forma bidirecional
    public void adicionarAresta(int origem, int destino, double peso) {
        this.matrizAdjacencia[origem][destino] = peso;
        // O grafo não é direcionado, ou seja, a distância de A para B é a mesma de B para A
        this.matrizAdjacencia[destino][origem] = peso;
    }

    // Consulta qual é a distância cadastrada entre duas cidades
    public double getPeso(int origem, int destino) {
        return matrizAdjacencia[origem][destino];
    }

    // Retorna o número total de cidades no grafo
    public int getNumeroVertices() {
        return numeroVertices;
    }

    // Associa as coordenadas espaciais ao grafo
    public void setCoordenadas(double[][] coordenadas) {
        this.coordenadas = coordenadas;
    }

    // Retorna as coordenadas armazanadas
    public double[][] getCoordenadas() {
        return coordenadas;
    }
}