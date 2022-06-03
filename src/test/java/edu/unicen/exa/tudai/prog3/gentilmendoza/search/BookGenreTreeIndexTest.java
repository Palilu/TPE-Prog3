package edu.unicen.exa.tudai.prog3.gentilmendoza.search;

import edu.unicen.exa.tudai.prog3.gentilmendoza.main.CSVFilter;
import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Dataset;
import edu.unicen.exa.tudai.prog3.gentilmendoza.util.Metrics;

public class BookGenreTreeIndexTest {

    private static CSVFilter csvFilter = new CSVFilter();

    public static void main(String[] args) {
        Metrics metrics = new Metrics();
        for (Dataset dataset : Dataset.values()) {
            csvFilter.filter(metrics, dataset, Index.TREE,"ciencia");
        }
        metrics.analyze();
    }
}
