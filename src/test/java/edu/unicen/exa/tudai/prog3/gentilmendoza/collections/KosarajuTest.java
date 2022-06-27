package edu.unicen.exa.tudai.prog3.gentilmendoza.collections;

public class KosarajuTest {

    public static void main(String[] args) {

        Graph<Integer, String> g = new Graph<>();
        g.setEdge(0,1, "");
        g.setEdge(1,2, "");
        g.setEdge(2,3, "");
        g.setEdge(2,4, "");
        g.setEdge(3,0, "");
        g.setEdge(4,5, "");
        g.setEdge(5,6, "");
        g.setEdge(6,4, "");
        g.setEdge(7,6, "");
        g.setEdge(7,8, "");

        g.getStronglyConnectedComponents(0);
    }
}
