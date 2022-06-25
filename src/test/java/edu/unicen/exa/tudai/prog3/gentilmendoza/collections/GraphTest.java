package edu.unicen.exa.tudai.prog3.gentilmendoza.collections;

public class GraphTest {

    public static void main(String[] args) {
        Graph<String, String> pastaGraph = new Graph<>();
        pastaGraph.setEdge("Cacio e pepe", "Alla Gricia", "Guanciale");
        pastaGraph.setEdge("Alla Gricia", "Carbonara", "Uovo");
        pastaGraph.setEdge("Alla Gricia", "All\'amatriciana", "Pomodoro");
        System.out.println(pastaGraph.exportForGraphvizOnline("Pasta"));

        Graph<String, Integer> shortcutGraph = new Graph();
        shortcutGraph.setEdge("A", "D", 5);
        shortcutGraph.setEdge("A", "B", 1);
        shortcutGraph.setEdge("B", "C", 1);
        shortcutGraph.setEdge("C", "D", 1);
        System.out.println(shortcutGraph.exportForGraphvizOnline("SC"));
    }
}
