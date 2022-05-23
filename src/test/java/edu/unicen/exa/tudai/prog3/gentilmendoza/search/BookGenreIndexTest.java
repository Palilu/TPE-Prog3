package edu.unicen.exa.tudai.prog3.gentilmendoza.search;

import edu.unicen.exa.tudai.prog3.gentilmendoza.main.CSVFilter;
import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Dataset;
import edu.unicen.exa.tudai.prog3.gentilmendoza.util.Metrics;

import java.io.IOException;

public class BookGenreIndexTest {

    private static CSVFilter csvFilter = new CSVFilter();

    public static void main(String[] args) throws IOException {
        Metrics metrics = new Metrics();
        for (Dataset dataset : Dataset.values()) {
            for (Index index : Index.values()) {
                for (int i = 0; i < 100; i++) {
                    System.out.println(dataset.getFilename() + " " + index + " " + i);
                    csvFilter.filter(metrics, dataset, index, i,"ciencia");
                }
            }
        }
        metrics.analyze();
    }


}
