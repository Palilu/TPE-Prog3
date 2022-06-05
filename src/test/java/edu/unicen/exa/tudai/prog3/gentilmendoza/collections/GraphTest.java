package edu.unicen.exa.tudai.prog3.gentilmendoza.collections;

public class GraphTest {

    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        graph.addEdge("Cacio e pepe", "Alla Gricia");
        graph.addEdge("Alla Gricia", "Carbonara");
        graph.addEdge("Alla Gricia", "All\'amatriciana");
        graph.addEdge("Alla Gricia", "All\'amatriciana");
        System.out.println(graph.exportForGraphvizOnline("G"));
    }
}
