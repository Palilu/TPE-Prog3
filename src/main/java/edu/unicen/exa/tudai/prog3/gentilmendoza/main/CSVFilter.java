package edu.unicen.exa.tudai.prog3.gentilmendoza.main;

import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Book;
import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Dataset;
import edu.unicen.exa.tudai.prog3.gentilmendoza.search.BookGenreIndex;
import edu.unicen.exa.tudai.prog3.gentilmendoza.search.BookGenreIndexFactory;
import edu.unicen.exa.tudai.prog3.gentilmendoza.search.Index;
import edu.unicen.exa.tudai.prog3.gentilmendoza.util.CSVMapper;
import edu.unicen.exa.tudai.prog3.gentilmendoza.util.Metrics;

import java.io.IOException;
import java.util.List;

public class CSVFilter {

    private static final String INPUT_FOLDER = "src/main/resources/datasets/";
    private static final String OUTPUT_FOLDER = "src/main/resources/results/";
    private static final String SEPARATOR = "_";

    private BookGenreIndexFactory indexFactory = new BookGenreIndexFactory();

    private CSVMapper csvMapper = new CSVMapper();

    public void filter(Metrics metrics, Dataset dataset, Index index, Integer attempt, String genre) throws IOException {

        List<Book> books = csvMapper.readCSVFile(INPUT_FOLDER + dataset.getFilename());
        metrics.addMetric(dataset, index, "fileParsing");
        if (books.isEmpty()) {
            return;
        }
        BookGenreIndex indexObj = indexFactory.getBookGenreIndex(index, books);
        metrics.addMetric(dataset, index," indexCreation");
        List<Book> filtered = indexObj.search(genre);
        metrics.addMetric(dataset, index," searchByGenre");
        StringBuilder filenameBuilder = new StringBuilder(OUTPUT_FOLDER)
                    .append(genre)
                    .append(SEPARATOR)
                    .append(index.getLabel().trim().toLowerCase())
                    .append(SEPARATOR)
                    .append(dataset.getFilename().split("\\.")[0])
                    .append(SEPARATOR)
                    .append("results.csv");
        csvMapper.writeCSVFile(filenameBuilder.toString(), filtered);
        metrics.addMetric(dataset, index," fileCreation");
    }
}
