package edu.unicen.exa.tudai.prog3.gentilmendoza.collections;

import java.util.*;

import static java.util.function.Predicate.not;

/**
 * @param <V> El tipo de los Vértices (Vertex)
 * @param <E> El tipo de las aristas (Edges)
 */
public class Graph<V, E> {

    // Constantes para salida en formato graphviz
    private static final String GRAPHVIZ_BEGIN = "digraph ";
    private static final String GRAPHVIZ_BLOCK_BEGIN = " { ";
    private static final String GRAPHVIZ_BLOCK_END = " }";
    private static final String GRAPHVIZ_QUOTE = "\"";
    private static final String GRAPHVIZ_EDGE = " -> ";
    private static final String GRAPHVIZ_LINE = "\n";
    private static final String GRAPHVIZ_LABEL_BEGIN = " [label = ";
    private static final String GRAPHVIZ_LABEL_END = "];";

    // Representación del grafo
    private Map<V, Map<V, E>> adjVertices = new HashMap<>();

    /**
     * Agrega una arista desde el vértice from hasta el vértice to con la etiqueta label.
     *
     * @param from El vértice origen de la arista.
     * @param to El vértice destino de la arista.
     * @param label La etiqueta de la arista.
     *
     */
    public void setEdge(V from, V to, E label) {
        adjVertices.putIfAbsent(from, new HashMap<>());
        adjVertices.get(from).put(to, label);
    }

    /**
     * Retorna, si existiera, la etiqueta de la arista entre dos vértices.
     *
     * @param from El vértice origen de la arista.
     * @param to El vértice destino de la arista.
     */
    public Optional<E> getLabel(V from, V to) {
        if (adjVertices.containsKey(from) && adjVertices.get(from).containsKey(to)) {
            return Optional.of(adjVertices.get(from).get(to));
        }
        return Optional.empty();
    }

    /**
     * Retorna la lista de vértices adyacentes a un nodo de origen.
     *
     * @param from El vértice origen.
     */
    public Set<V> getAdjacent(V from) {
        if (adjVertices.containsKey(from)) {
            return adjVertices.get(from).keySet();
        }
        return Collections.emptySet();
    }

    /**
     * Exporta el grafo en el formato de graphviz online.
     *
     * @param name El nombre del grafo.
     */
    public String exportForGraphvizOnline(String name) {
        StringBuilder result = new StringBuilder(GRAPHVIZ_BEGIN);
        result.append(name);
        result.append(GRAPHVIZ_BLOCK_BEGIN);
        result.append(GRAPHVIZ_LINE);
        this.adjVertices.entrySet().forEach(entry -> {
            V from = entry.getKey();
            entry.getValue().entrySet().forEach(edge -> {
                V to = edge.getKey();
                result.append(GRAPHVIZ_QUOTE);
                result.append(from);
                result.append(GRAPHVIZ_QUOTE);
                result.append(GRAPHVIZ_EDGE);
                result.append(GRAPHVIZ_QUOTE);
                result.append(to);
                result.append(GRAPHVIZ_QUOTE);
                result.append(GRAPHVIZ_LABEL_BEGIN);
                result.append(edge.getValue());
                result.append(GRAPHVIZ_LABEL_END);
                result.append(GRAPHVIZ_LINE);
            });
        });
        result.append(GRAPHVIZ_BLOCK_END);
        return result.toString();
    }

    /**
     * Retorna los vértices del grafo.
     */
    public Set<V> getVertices() {
        Set<V> result = new HashSet<>(adjVertices.keySet());
        adjVertices.entrySet().forEach(entry -> result.addAll(entry.getValue().keySet()));
        return result;
    }

    /**
     * Retorna la cantidad de aristas del grafo.
     */
    public Integer getEdgeCount() {
        return this.adjVertices.values().stream()
                .map(Map::size)
                .mapToInt(Integer::valueOf)
                .sum();
    }

    /**
     * Retorna los componentes fuertemente conectados de un grafo.
     *
     * Un grafo dirigido es llamado fuertemente conexo si para cada par de vértices u y v existe un camino
     * de u hacia v y un camino de v hacia u. Los componentes fuertemente conexos (CFC) de un grafo dirigido
     * son sus subgrafos maximales fuertemente conexos. Estos subgrafos forman una partición del grafo.
     *
     * Por ejemplo, en el grafo:
     *
     *     0     ←    3          5     →    6     ←    7
     *
     *     ↓          ↑          ↑     ↗               ↓
     *
     *     1     →    2     →    4                     8
     *
     *  Los componentes fuertemente conectados son:
     *     [[0, 1, 2, 3], [4, 5, 6], [7] y [8]]
     */
    public List<List<V>> getStronglyConnectedComponents(V vertex) {
        // Para ello vamos a implementar el algoritmo de Kosaraju.
        List<List<V>> result = new ArrayList<>();
        // 1. Obtenemos una pila con los vértices ordenados por orden
        // de finalización de la recursión si comenzamos una búsqueda en
        // profundidad a partir del género de origen.
        Stack<V> topologicalSearch = getTopologicalSearch(vertex);
        // 2. Transponemos el grafo original
        Graph<V, E> transposed = this.transpose();
        // 3. Y comenzando por el último vértice alcanzado desde el origen
        while (!topologicalSearch.isEmpty()) {
            // Obtenemos los vértices alcanzables en profundidad desde ellos en el grafo traspuesto.
            V current = topologicalSearch.pop();
            // Cada conjunto es un componente fuertemente conectado
            List<V> scc = transposed.deepFirstSearch(current, topologicalSearch);
            // Lo agregamos al resultado
            result.add(scc);
            // Y los sacamos de la búsqueda topológica y lista de nodos pendientes.
            topologicalSearch.removeAll(scc);
        }
        return result;
    }

    /**
     * Retorna una pila en la que los últimos elementos
     * descubiertos por un DFS a partir de un vértice están primeros.
     *
     *   0 ← 3    Por ejemplo, la búsqueda topológica a partir del   [ 3 ]
     *   ↓   ↑    vértice 0 del grafo de la izquierda retornaría     [ 2 ]
     *   1 → 2    la pila de la derecha.                             [ 1 ]
     *                                                               [ 0 ]
     *  Forma parte de la implementación de la primera parte del algoritmo de Kosaraju.
     */
    private Stack<V> getTopologicalSearch(V vertex) {
        Stack<V> result = new Stack<>();
        List<V> visited = new ArrayList<>();
        Set<V> vertices = this.getVertices();
        Optional<V> current = Optional.of(vertex);
        do {
            getTopologicalSearch(result, visited, current.get());
            current = vertices.stream().filter(not(visited::contains)).findFirst();
        } while(current.isPresent());
        return result;
    }

    private void getTopologicalSearch(Stack<V> stack,
                                      List<V> visited,
                                      V current) {
        visited.add(current);
        getAdjacent(current).stream()
                .filter(adj -> !visited.contains(adj))
                .forEach(adj -> getTopologicalSearch(stack, visited, adj));
        stack.push(current);
    }

    /**
     * Retorna el grafo transpuesto.
     *
     *   0 ← 3    Por ejemplo, el grafo transpuesto     0 → 3
     *   ↓   ↑    del grafo de la izquierda retornaría  ↑   ↓
     *   1 → 2    el grafo de la derecha.               1 ← 2
     *
     * Forma parte de la implementación de la segunda parte del algoritmo de Kosaraju.
     */
    private Graph<V, E> transpose() {
        Graph<V, E> transposed = new Graph<>();
        this.adjVertices.keySet().forEach(v1 ->
                this.adjVertices.get(v1).entrySet()
                        .forEach(adj -> transposed.setEdge(adj.getKey(), v1, adj.getValue()))
        );
        return transposed;
    }

    /**
     * Retorna la lista de vertices que pueden ser alcanzados
     * a partir de un nodo origen, mientras se encuentren
     * todavía en una pila de nodos pendientes pasada como parámetro.
     *
     * Forma parte de la implementación de la tercera parte del algoritmo de Kosaraju.
     *
     * @param source El vértice de origen.
     * @param pending La lista de nodos pendintes.
     */
    private List<V> deepFirstSearch(V source,
                                    Stack<V> pending) {
        List<V> visited = new ArrayList<>();
        deepFirstSearch(source, visited, pending);
        return visited;
    }

    private void deepFirstSearch(V current,
                                 List<V> visited,
                                 Stack<V> stack) {
        visited.add(current);
        getAdjacent(current).stream()
                .filter(vertex -> stack.contains(vertex) && !visited.contains(vertex))
                .forEach(adj -> deepFirstSearch(adj, visited, stack));
    }
}
