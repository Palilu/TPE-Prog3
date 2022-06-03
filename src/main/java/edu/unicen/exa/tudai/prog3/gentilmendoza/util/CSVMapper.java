package edu.unicen.exa.tudai.prog3.gentilmendoza.util;

import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Book;

import java.util.List;

public interface CSVMapper {

    List<Book> readCSVFile(String filename);

    void writeCSVFile(String filename, List<Book> books);
}
