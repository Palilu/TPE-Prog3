package edu.unicen.exa.tudai.prog3.gentilmendoza.collections;

import java.util.*;

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

    public void setEdge(V from, V to, E label) {
        adjVertices.putIfAbsent(from, new HashMap<>());
        adjVertices.get(from).put(to, label);;
    }

    public Optional<E> getLabel(V from, V to) {
        if (adjVertices.containsKey(from) && adjVertices.get(from).containsKey(to)) {
            return Optional.of(adjVertices.get(from).get(to));
        }
        return Optional.empty();
    }

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
}
