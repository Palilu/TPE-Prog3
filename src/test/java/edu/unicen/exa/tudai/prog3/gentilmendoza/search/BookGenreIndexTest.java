package edu.unicen.exa.tudai.prog3.gentilmendoza.search;

import edu.unicen.exa.tudai.prog3.gentilmendoza.main.CSVFilter;
import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Book;
import edu.unicen.exa.tudai.prog3.gentilmendoza.util.CSVMapper;
import edu.unicen.exa.tudai.prog3.gentilmendoza.util.Timer;

import java.io.IOException;
import java.util.List;

public class BookGenreIndexTest {

    private static List<String> DATASETS = List.of(
            "src/main/resources/datasets/dataset1.csv",
            "src/main/resources/datasets/dataset2.csv",
            "src/main/resources/datasets/dataset3.csv",
            "src/main/resources/datasets/dataset4.csv");

    private static CSVFilter csvFilter = new CSVFilter();

    public static void main(String[] args) throws IOException {
        for (String filename : DATASETS) {
            for (Index index : Index.values()) {
                csvFilter.filter(filename, index, "ciencia");
            }
        }
    }


}
