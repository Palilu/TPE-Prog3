package edu.unicen.exa.tudai.prog3.gentilmendoza.main;

import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Dataset;

public class GraphServiceTest {

    public static void main(String[] args) {
        GraphService graphService = new GraphService();
        graphService.generateGraphAndRunServices(Dataset.D1);
    }
}
