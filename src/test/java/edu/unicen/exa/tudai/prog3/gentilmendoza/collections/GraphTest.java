package edu.unicen.exa.tudai.prog3.gentilmendoza.collections;

public class GraphTest {

    public static void main(String[] args) {
        Graph<String> pastaGraph = new Graph<>();
        pastaGraph.addEdge("Cacio e pepe", "Alla Gricia");
        pastaGraph.addEdge("Alla Gricia", "Carbonara");
        pastaGraph.addEdge("Alla Gricia", "All\'amatriciana");
        System.out.println(pastaGraph.exportForGraphvizOnline("G"));
        System.out.println(pastaGraph.dijkstra("Cacio e pepe").exportForGraphvizOnline("D"));

        Graph<String> shortcutGraph = new Graph<>();
        shortcutGraph.addEdge("A", "D");
        shortcutGraph.addEdge("A", "D");
        shortcutGraph.addEdge("A", "D");
        shortcutGraph.addEdge("A", "D");
        shortcutGraph.addEdge("A", "D");
        shortcutGraph.addEdge("A", "B");
        shortcutGraph.addEdge("B", "C");
        shortcutGraph.addEdge("C", "D");
        System.out.println(shortcutGraph.exportForGraphvizOnline("SC"));
        System.out.println(shortcutGraph.dijkstra("A").exportForGraphvizOnline("SCR"));
    }
}
