package edu.unicen.exa.tudai.prog3.gentilmendoza.collections;

import java.lang.reflect.Array;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * Obtener los N géneros más buscados luego de buscar por el género A.
     */
    public List<Vertex<S>> getMostSearchAfter(S a, Integer n) {
        Vertex<S> aVertex = new Vertex<>(a);
        return adjVertices.get(aVertex).entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(entry -> entry.getKey())
                .collect(Collectors.toList())
                .subList(0, n);
    }

    public String exportForGraphvizOnline(String name) {
        StringBuilder result = new StringBuilder(GRAPHVIZ_BEGIN);
        result.append(name);
        result.append(GRAPHVIZ_BLOCK_BEGIN);
        result.append(GRAPHVIZ_LINE);
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




    /**
     * 1  function Dijkstra(Graph, source):
     * 2
     * 3      for each vertex v in Graph.Vertices:
     * 4          dist[v] ← INFINITY
     * 5          prev[v] ← UNDEFINED
     * 6          add v to Q
     * 7      dist[source] ← 0
     * 8
     * 9      while Q is not empty:
     * 10          u ← vertex in Q with min dist[u]
     * 11          remove u from Q
     * 12
     * 13          for each neighbor v of u still in Q:
     * 14              alt ← dist[u] + Graph.Edges(u, v)
     * 15              if alt < dist[v] and dist[u] is not INFINITY:
     * 16                  dist[v] ← alt
     * 17                  prev[v] ← u
     * 18
     * 19      return dist[], prev[]
     */
    public Graph<S> dijkstra(S source) {
        Vertex<S> sourceVertex = new Vertex<>(source);
        // Obtenemos el conjunto vertices y los ponemos en una lista para que tengan un orden.
        List<Vertex<S>> vertices = new ArrayList<>(getVertices());
        // Hacemos una copia de esa lista
        List<Vertex<S>> q = new ArrayList<>(vertices);
        // Encontramos el índice del vértice de origen
        Integer sourceIndex = vertices.indexOf(sourceVertex);
        if (sourceIndex < 0) {
            // Si no existe el parámetro no es válido.
            throw new InvalidParameterException("Un vértice que exista en el grafo genio.");
        }
        // Declaramos una lista del mismo tamaño que vertices para almacenar las distancias menores
        List<Integer> dist = new ArrayList<>(vertices.size());
        // Y otra que almacenará el nodo previo para conseguir esa distancia si lo hubiere.
        List<Optional<Vertex<S>>> prev = new ArrayList<>(vertices.size());
        // Inicializamos las distancias en infinito y los nodos previos vacíos.
        for (int i = 0; i < vertices.size(); i++) {
            dist.add(i, Integer.MAX_VALUE);
            prev.add(i, Optional.empty());
        }
        // La distancia al nodo origen es 0, no tiene previo pero ya está asignado.
        dist.set(sourceIndex, 0);
        // Mientras tenga vertices en mi cola
        while (!q.isEmpty()) {
            // Obtengo el índice del vértice que todavía está en la cola con la distancia más chica.
            Integer currentIndex = getMinDistanceNonProcessedVertex(vertices, q, dist);
            // Obtengo su índice
            Vertex<S> current = vertices.get(currentIndex);
            // Y lo saco de la cola
            q.remove(current);
            // Si current tiene vertices adyacentes
            if (adjVertices.get(current) != null) {
                adjVertices.get(current).entrySet().stream() // Para cada vértice adyacente
                        .filter(entry -> q.contains(entry.getKey())) // Que todavía esté en la cola
                        .forEach(entry -> {
                            // Calculamos la distancia alternativa de llegar a current y desde current a su adyacente v
                            Integer alt = dist.get(currentIndex) + entry.getValue();
                            int vIndex = vertices.indexOf(entry.getKey());
                            // Si la distancia alternativa es menor a la actual
                            // && dist.get(currentIndex) != Integer.MAX_VALUE <- esto no se necesita creo
                            if (alt < dist.get(vIndex)) {
                                // Entonces la distancia a v es la alternativa
                                dist.set(vIndex, alt);
                                // Y la forma de llegar con esa mejor distancia es viniendo de current.
                                prev.set(vIndex, Optional.of(current));
                            }
                        });
            }
        }
        // Retornamos el grafo formado por los vertices de cada nodo desde su nodo previo.
        Graph<S> result = new Graph<>();
        for (int i = 0; i < vertices.size(); i++) {
            if (prev.get(i).isPresent()) {
                result.addEdge(prev.get(i).get().getLabel(), vertices.get(i).getLabel());
            }
        }
        return result;
    }

    private Integer getMinDistanceNonProcessedVertex(List<Vertex<S>> vertices,
                                                     List<Vertex<S>> q,
                                                     List<Integer> dist) {

        Integer minDistance = Integer.MAX_VALUE;
        Integer minDistIndex = null;
        for (Vertex<S> candidate : q) {
            int candidateIndex = vertices.indexOf(candidate);
            if (dist.get(candidateIndex) < minDistance) {
                minDistance = dist.get(candidateIndex);
                minDistIndex = candidateIndex;
            }
        }
        return minDistIndex;
    }

    private Set<Vertex<S>> getVertices() {
        Set<Vertex<S>> result = new HashSet<>(adjVertices.keySet());
        adjVertices.entrySet().forEach(entry -> {
            result.addAll(entry.getValue().keySet());
        });
        return result;
    }
}
