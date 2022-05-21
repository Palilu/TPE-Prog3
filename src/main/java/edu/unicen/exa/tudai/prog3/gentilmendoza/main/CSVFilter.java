package edu.unicen.exa.tudai.prog3.gentilmendoza.main;

import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Book;
import edu.unicen.exa.tudai.prog3.gentilmendoza.search.BookGenreHashMapIndex;
import edu.unicen.exa.tudai.prog3.gentilmendoza.search.BookGenreIndex;
import edu.unicen.exa.tudai.prog3.gentilmendoza.search.BookGenreIndexFactory;
import edu.unicen.exa.tudai.prog3.gentilmendoza.search.Index;
import edu.unicen.exa.tudai.prog3.gentilmendoza.util.CSVMapper;
import edu.unicen.exa.tudai.prog3.gentilmendoza.util.Timer;

import java.io.IOException;
import java.util.List;

public class CSVFilter {

    private BookGenreIndexFactory indexFactory = new BookGenreIndexFactory();

    private CSVMapper csvMapper = new CSVMapper();

    public void filter(String filename, Index index, String genre) throws IOException {
        System.out.print(index.getLabel() + " index over " + filename);
        Timer timer = new Timer();
        List<Book> books = csvMapper.readCSVFile(filename);
        timer.mark(" read CSV ");
        BookGenreIndex hashmapIndex = indexFactory.getBookGenreIndex(index, books);
        timer.mark(" create index ");
        List<Book> filtered = hashmapIndex.search(genre);
        timer.mark(" search " + genre + " ");
        csvMapper.writeCSVFile("src/main/resources/datasets/" + genre + index.getLabel().trim() + "SearchResults.csv", filtered);
        timer.mark(" write CSV ");
        System.out.println(".");
    }
}
