package edu.unicen.exa.tudai.prog3.gentilmendoza.main;

import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Book;
import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Dataset;
import edu.unicen.exa.tudai.prog3.gentilmendoza.search.BookGenreIndex;
import edu.unicen.exa.tudai.prog3.gentilmendoza.search.BookGenreIndexFactory;
import edu.unicen.exa.tudai.prog3.gentilmendoza.search.Index;
import edu.unicen.exa.tudai.prog3.gentilmendoza.util.CSVMapper;
import edu.unicen.exa.tudai.prog3.gentilmendoza.util.CSVMapperImpl;
import edu.unicen.exa.tudai.prog3.gentilmendoza.util.Metrics;

import java.util.List;

public class CSVFilter {

    private static final String OUTPUT_FOLDER = "src/main/resources/results/";
    private static final String SEPARATOR = "_";

    private BookGenreIndexFactory indexFactory = new BookGenreIndexFactory();

    private CSVMapper csvMapper = new CSVMapperImpl();

    public void filter(Metrics metrics,
                       Dataset dataset,
                       Index index,
                       String genre) {
        // Leemos el CSV
        List<Book> books = csvMapper.readBooksCSVFile(dataset.getBookPath());
        metrics.addMetric(dataset, index, "fileParsing");
        if (books.isEmpty()) {
            return;
        }
        // Creamos el índice
        BookGenreIndex indexObj = indexFactory.getBookGenreIndex(index, books);
        metrics.addMetric(dataset, index,"indexCreation");
        // Buscamos por un género dado
        List<Book> filtered = indexObj.search(genre);
        metrics.addMetric(dataset, index,"searchByGenre");
        // Escribimos la salida en otro CSV
        csvMapper.writeCSVFile(getOutputFileName(dataset, index, genre), filtered);
        metrics.addMetric(dataset, index,"fileCreation");
    }

    private String getOutputFileName(Dataset dataset, Index index, String genre) {
        return new StringBuilder(OUTPUT_FOLDER)
                .append(genre)
                .append(SEPARATOR)
                .append(index.getLabel().trim().toLowerCase())
                .append(SEPARATOR)
                .append(dataset.getFilename().split("\\.")[0])
                .append(SEPARATOR)
                .append("results.csv").toString();
    }
}
