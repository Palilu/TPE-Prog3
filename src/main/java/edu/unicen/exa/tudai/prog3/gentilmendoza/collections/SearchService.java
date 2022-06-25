package edu.unicen.exa.tudai.prog3.gentilmendoza.collections;

import java.util.*;

public class SearchService {







//    /**
//     * 1  function Dijkstra(Graph, source):
//     * 2
//     * 3      for each vertex v in Graph.Vertices:
//     * 4          dist[v] ← INFINITY
//     * 5          prev[v] ← UNDEFINED
//     * 6          add v to Q
//     * 7      dist[source] ← 0
//     * 8
//     * 9      while Q is not empty:
//     * 10          u ← vertex in Q with min dist[u]
//     * 11          remove u from Q
//     * 12
//     * 13          for each neighbor v of u still in Q:
//     * 14              alt ← dist[u] + Graph.Edges(u, v)
//     * 15              if alt < dist[v] and dist[u] is not INFINITY:
//     * 16                  dist[v] ← alt
//     * 17                  prev[v] ← u
//     * 18
//     * 19      return dist[], prev[]
//     * Retorna la suma de los pesos de las aristas del grafo.
//     */
//    public GraphService dijkstra(String sourceVertex) {
//        // Obtenemos el conjunto vertices y los ponemos en una lista para que tengan un orden.
//        List<String> vertices = new ArrayList<>(getVertices());
//        // Hacemos una copia de esa lista
//        List<String> q = new ArrayList<>(vertices);
//        // Encontramos el índice del vértice de origen
//        Integer sourceIndex = vertices.indexOf(sourceVertex);
//        if (sourceIndex < 0) {
//            // Si no existe el parámetro no es válido.
//            throw new InvalidParameterException("Un vértice que exista en el grafo genio.");
//        }
//        // Declaramos una lista del mismo tamaño que vertices para almacenar las distancias menores
//        List<Integer> dist = new ArrayList<>(vertices.size());
//        // Y otra que almacenará el nodo previo para conseguir esa distancia si lo hubiere.
//        List<Optional<String>> prev = new ArrayList<>(vertices.size());
//        // Inicializamos las distancias en infinito y los nodos previos vacíos.
//        for (int i = 0; i < vertices.size(); i++) {
//            dist.add(i, Integer.MAX_VALUE);
//            prev.add(i, Optional.empty());
//        }
//        // La distancia al nodo origen es 0, no tiene previo pero ya está asignado.
//        dist.set(sourceIndex, 0);
//        // Mientras tenga vertices en mi cola
//        while (!q.isEmpty()) {
//            // Obtengo el índice del vértice que todavía está en la cola con la distancia más chica.
//            Integer currentIndex = getMinDistanceNonProcessedVertex(vertices, q, dist);
//            // Obtengo su índice
//            String current = vertices.get(currentIndex);
//            // Y lo saco de la cola
//            q.remove(current);
//            // Si current tiene vertices adyacentes
//            if (adjVertices.get(current) != null) {
//                adjVertices.get(current).entrySet().stream() // Para cada vértice adyacente
//                        .filter(entry -> q.contains(entry.getKey())) // Que todavía esté en la cola
//                        .forEach(entry -> {
//                            // Calculamos la distancia alternativa de llegar a current y desde current a su adyacente v
//                            Integer alt = dist.get(currentIndex) + entry.getValue();
//                            int vIndex = vertices.indexOf(entry.getKey());
//                            // Si la distancia alternativa es menor a la actual
//                            // && dist.get(currentIndex) != Integer.MAX_VALUE <- esto no se necesita creo
//                            if (alt < dist.get(vIndex)) {
//                                // Entonces la distancia a v es la alternativa
//                                dist.set(vIndex, alt);
//                                // Y la forma de llegar con esa mejor distancia es viniendo de current.
//                                prev.set(vIndex, Optional.of(current));
//                            }
//                        });
//            }
//        }
//        // Retornamos el grafo formado por los vertices de cada nodo desde su nodo previo.
//        GraphService result = new GraphService();
//        for (int i = 0; i < vertices.size(); i++) {
//            if (prev.get(i).isPresent()) {
//                result.addEdge(prev.get(i).get(), vertices.get(i));
//            }
//        }
//        return result;
//    }

//    private Integer getMinDistanceNonProcessedVertex(List<String> vertices,
//                                                     List<String> q,
//                                                     List<Integer> dist) {
//
//        Integer minDistance = Integer.MAX_VALUE;
//        Integer minDistIndex = null;
//        for (String candidate : q) {
//            int candidateIndex = vertices.indexOf(candidate);
//            if (dist.get(candidateIndex) < minDistance) {
//                minDistance = dist.get(candidateIndex);
//                minDistIndex = candidateIndex;
//            }
//        }
//        return minDistIndex;
//    }

//    /**
//     * 3. Obtener el grafo únicamente con los géneros afines a un género A; es decir que,
//     * partiendo del género A, se consiguió una vinculación cerrada entre uno o más
//     * géneros que permitió volver al género de inicio.
//     */
//    public GraphService findRelatedGenres(String genre) {
//        Stack<String> tsResult = topologicalSearch(genre);
//        System.out.println("Topological Search:");
//        System.out.println(tsResult);
//        GraphService transposed = transpose();
//        return null;
//    }

//    private List<List<String>> getStronglyConnectedComponents(Stack<String> tsResult) {
//        List<List<String>> result = new ArrayList<>();
//        List<String> visited = new ArrayList<>();
//        List<String> temp = new ArrayList<>();
//        doGetStronglyConnectedComponents(result, visited, temp, tsResult);
//        return result;
//    }

//    private void doGetStronglyConnectedComponents(List<List<String>> result,
//                                                  List<String> visited,
//                                                  List<String> temp,
//                                                  Stack<String> tsResult) {
//        List<String> options;
//
//
//    }

//    private Stack<String> topologicalSearch(String genre) {
//        Stack<String> result = new Stack<>();
//        List<String> visited = new ArrayList<>();
//        doTopologicalSearch(result, visited, genre);
//        return result;
//    }

//    private void doTopologicalSearch(Stack<String> stack,
//                                     List<String> visited,
//                                     String current) {
//        visited.add(current);
//        if (this.adjVertices.containsKey(current)) {
//            this.adjVertices.get(current).keySet().stream()
//                    .filter(not(visited::contains))
//                    .forEach(adj -> doTopologicalSearch(stack, visited, adj));
//        }
//        stack.push(current);
//    }

//    private GraphService transpose() {
//        GraphService transposed = new GraphService();
//        this.adjVertices.keySet().forEach(v1 ->
//            this.adjVertices.get(v1).keySet().forEach(v2 -> transposed.addEdge(v2, v1))
//        );
//        return transposed;
//    }
}
