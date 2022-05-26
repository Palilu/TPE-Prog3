package edu.unicen.exa.tudai.prog3.gentilmendoza.search;

import edu.unicen.exa.tudai.prog3.gentilmendoza.main.CSVFilter;
import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Dataset;
import edu.unicen.exa.tudai.prog3.gentilmendoza.util.Metrics;

import java.io.IOException;

public class BookGenreTreeIndexTest {

    private static CSVFilter csvFilter = new CSVFilter();

    public static void main(String[] args) throws IOException {
        Metrics metrics = new Metrics();
        for (Dataset dataset : Dataset.values()) {
        	System.out.println(dataset.getFilename() + " " + "TREE" );
        	csvFilter.filter(metrics, dataset, Index.TREE ,1,"ciencia");
        }
        metrics.analyze();
    }


}
