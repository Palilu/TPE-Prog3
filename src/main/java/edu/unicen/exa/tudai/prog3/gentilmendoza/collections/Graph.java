package edu.unicen.exa.tudai.prog3.gentilmendoza.collections;

import java.util.HashMap;
import java.util.Map;

public class Graph<S> {
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
    private Map<Vertex<S>, Map<Vertex<S>, Integer>> adjVertices = new HashMap<>();

    /**
     * Agrega 1 a la cuenta de ocurrencias desde el vértice con la etiqueta label1
     * hasta el vértice con la etiqueta label2.
     *
     * @param label1
     * @param label2
     */
    public void addEdge(S label1, S label2) {
        Vertex v1 = new Vertex<>(label1);
        Vertex v2 = new Vertex<>(label2);
        adjVertices.putIfAbsent(v1, new HashMap<>());
        Map<Vertex<S>, Integer> edges = adjVertices.get(v1);
        edges.putIfAbsent(v2, 0);
        edges.put(v2, adjVertices.get(v1).get(v2) + 1);
    }

    public String exportForGraphvizOnline(String name) {
        StringBuilder result = new StringBuilder(GRAPHVIZ_BEGIN);
        result.append(name);
        result.append(GRAPHVIZ_BLOCK_BEGIN);
        this.adjVertices.entrySet().forEach(entry -> {
            Vertex<S> from = entry.getKey();
            entry.getValue().entrySet().forEach(edge -> {
                Vertex<S> to = edge.getKey();
                result.append(GRAPHVIZ_QUOTE);
                result.append(from.getLabel());
                result.append(GRAPHVIZ_QUOTE);
                result.append(GRAPHVIZ_EDGE);
                result.append(GRAPHVIZ_QUOTE);
                result.append(to.getLabel());
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
}
