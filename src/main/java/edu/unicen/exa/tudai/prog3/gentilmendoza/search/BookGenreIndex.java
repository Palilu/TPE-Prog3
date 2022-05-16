package edu.unicen.exa.tudai.prog3.gentilmendoza.search;

import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Book;

import java.util.List;

public interface BookGenreIndex {

    List<Book> search(String genre);
}
