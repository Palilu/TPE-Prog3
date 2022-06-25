package edu.unicen.exa.tudai.prog3.gentilmendoza;

import edu.unicen.exa.tudai.prog3.gentilmendoza.main.GraphService;
import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Dataset;

public class MainTest {

    public static void main(String[] args) {
        GraphService graphService = new GraphService();
        for (Dataset dataset: Dataset.values()) {
            System.out.println("Processing dataset:" + dataset.getFilename());
            graphService.generateGraphAndRunServices(dataset);
        }
    }
}
