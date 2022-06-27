package edu.unicen.exa.tudai.prog3.gentilmendoza.main;

import edu.unicen.exa.tudai.prog3.gentilmendoza.collections.Graph;
import edu.unicen.exa.tudai.prog3.gentilmendoza.collections.Pair;
import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Dataset;
import edu.unicen.exa.tudai.prog3.gentilmendoza.util.CSVMapper;
import edu.unicen.exa.tudai.prog3.gentilmendoza.util.CSVMapperImpl;

import java.util.*;

import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class GraphService {

    private final CSVMapper csvMapper = new CSVMapperImpl();

    public void generateGraphAndRunServices(Dataset dataset) {
        // Leemos el CSV
        List<List<String>> searches = csvMapper.readSearchCSVFile(dataset.getSearchPath());
        // Creamos un grafo con las búsquedas
        Graph<String, Integer> genreSearchGraph = createGenreSearchGraph(searches);
        // Analizamos el resultado
        System.out.println(genreSearchGraph.getVertices().size() + " Vertices.");
        System.out.println(genreSearchGraph.getEdgeCount() + " Aristas.");
        System.out.println(getEdgesWeight(genreSearchGraph) + " Ocurrencias.");
        // Obtener los N géneros más buscados luego de buscar por el género A.
        System.out.println("Servicio #1 ");
        List.of("informática", "tecnología", "ciencia").forEach(genre -> {
            System.out.print("5 más buscados después de " + genre);
            System.out.print(getMostSearchAfter(genreSearchGraph, genre, 5));
            System.out.println(".");
        });
        System.out.println("Servicio #2 ");
        List.of("informática", "tecnología", "ciencia").forEach(genre -> {
            System.out.print("Más alto valor de búsqueda de " + genre);
            System.out.print(getPolinomialHighSearchValue(genreSearchGraph, genre));
            System.out.println(".");
        });
        System.out.println("Servicio #3 ");
        // Obtener el grafo únicamente con los géneros afines a un género A; es decir que,
        // partiendo del género A, se consiguió una vinculación cerrada entre uno o más
        // géneros que permitió volver al género de inicio.
        Graph<String, Integer> alike = getAlikeGenres(genreSearchGraph, "informática");
        System.out.println(alike.getVertices().size() + " Vertices.");
        System.out.println(alike.getEdgeCount() + " Aristas.");
        System.out.println(getEdgesWeight(alike) + " Ocurrencias.");
    }

    /**
     * Retorna un grafo, donde:
     *      ● Cada vértice representa un género que fue incluido en alguna búsqueda; y
     *      ● Un arco que comunica dos vértices indicará la cantidad de veces que luego de
     *        buscar el primer género inmediatamente a continuación se buscó por el segundo
     *        género.
     *
     * @param searches Se proveerá como entrada los sucesivos géneros que se ingresaron en distintas
     *                búsquedas realizadas.
     */
    public Graph<String, Integer> createGenreSearchGraph(List<List<String>> searches) {
        // Creamos el grafo
        Graph<String, Integer> genreSearchGraph = new Graph<>();
        // Cargamos las búsquedas en el grafo
        searches.forEach(search -> {
            for (int i = 1; i < search.size(); i++) {
                String v1 = search.get(i-1);
                String v2 = search.get(i);
                genreSearchGraph.setEdge(v1, v2, genreSearchGraph.getLabel(v1, v2).orElse(0) + 1);
            }
        });
        // Y lo retornamos
        return genreSearchGraph;
    }

    /**
     * Retorna la suma de los pesos de las aristas del grafo.
     */
    public Integer getEdgesWeight(Graph<String, Integer> graph) {
        return graph.getVertices().stream()
                .map(from -> graph.getAdjacent(from).stream()
                                .map(to -> graph.getLabel(from, to).get())
                                .collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .mapToInt(Integer::valueOf)
                .sum();
    }

    /**
     * 1. Obtener los N géneros más buscados luego de buscar por un género A.
     *
     * @param graph El grafo.
     * @param genre El género A.
     * @param n La cantidad de géneros más buscados
     */
    public List<Pair<String, Integer>> getMostSearchAfter(Graph<String, Integer> graph, String genre, Integer n) {
        List<Pair<String, Integer>> sorted = graph.getAdjacent(genre).stream()
                        .map(adj -> new Pair<>(adj, graph.getLabel(genre, adj).get()))
                        .sorted(Collections.reverseOrder(Comparator.comparing(Pair::getRight)))
                        .collect(Collectors.toList());
        return sorted.subList(0, Math.min(n, sorted.size()));
    }

    /**
     * 2. A partir de un género A encontrar, en tiempo polinomial, la secuencia de géneros
     * que más alto valor de búsqueda posee. Vamos a definir el valor de búsqueda de
     * una secuencia como la suma de los arcos que la componen.
     *
     * @param graph El grafo de búsquedas
     * @param genre El género A
     */
    public List<Pair<String, Integer>> getPolinomialHighSearchValue(Graph<String, Integer> graph,
                                                                    String genre) {
        List<String> visited = new ArrayList<>();
        visited.add(genre);
        List<Integer> searchValues = new ArrayList<>();
        searchValues.add(0);
        doGetPolinomialHighSearchValue(graph, genre, visited, searchValues);
        return visited.stream()
                .map(g -> new Pair<>(g, searchValues.get(visited.indexOf(g))))
                .collect(Collectors.toList());
    }

    /**
     * Función recursiva para encontrar un valor de búsqueda alto en tiempo polinomial.
     *
     * @param graph El grafo de búsquedas.
     * @param current El vértice actual
     * @param visited Una lista con los vértices visitados.
     * @param searchValues Una lista con los valores de búsqueda de los vértices visitados.
     */
    private void doGetPolinomialHighSearchValue(Graph<String, Integer> graph,
                                                String current,
                                                List<String> visited,
                                                List<Integer> searchValues) {
        graph.getAdjacent(current).stream()// Para cada adyacente
                .filter(adj -> !visited.contains(adj)) // Que no fué visitado
                .map(adj -> new Pair<>(adj, graph.getLabel(current, adj).get())) // Hacemos un par vértice, valor de búsqueda
                .max(Comparator.comparing(Pair::getRight)) // Nos quedamos con el mayor valor de búsqueda
                .ifPresent(max -> { // Si existe (Podríamos haber visitado todos los adyacentes)
                    visited.add(max.getLeft()); // Lo agregamos a los visitados
                    searchValues.add(max.getRight()); // Agregamos el valor de búsqueda
                    doGetPolinomialHighSearchValue(graph, max.getLeft(), visited, searchValues); // Y llamamos recursivamente desde ahí.
                });
    }

    /**
     * 3. Obtener el grafo únicamente con los géneros afines a un género A; es decir que,
     * partiendo del género A, se consiguió una vinculación cerrada entre uno o más
     * géneros que permitió volver al género de inicio.
     */
    private Graph<String, Integer> getAlikeGenres(Graph<String, Integer> graph, String genre) {
        Optional<List<String>> sscOpt = graph.getStronglyConnectedComponents(genre).stream()
                .filter(scc -> scc.contains(genre) && scc.size() != 1)
                .findFirst();
        Graph<String, Integer> result = new Graph<>();
        if (sscOpt.isPresent()) {
            List<String> vertices = sscOpt.get();
            vertices.forEach(vertex -> graph.getAdjacent(vertex).stream()
                    .filter(vertices::contains)
                    .forEach(sccAdj -> result.setEdge(vertex, sccAdj, graph.getLabel(vertex, sccAdj).get())));
        }
        return result;
    }
}
