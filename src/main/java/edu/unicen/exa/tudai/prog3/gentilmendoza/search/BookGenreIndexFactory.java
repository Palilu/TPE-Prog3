package edu.unicen.exa.tudai.prog3.gentilmendoza.search;

import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Book;

import java.util.List;

public class BookGenreIndexFactory {

    public BookGenreIndex getBookGenreIndex(Index index, List<Book> books) {
        switch (index) {
            case HASHMAP: return new BookGenreHashMapIndex(books);
            case ARRAYLIST: return new BookGenreArrayListIndex(books);
            case ARRAYLIST_NODE: return new BookGenreArrayListNodeIndex(books);
        }
        return null;
    }
}
