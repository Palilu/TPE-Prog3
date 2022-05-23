package edu.unicen.exa.tudai.prog3.gentilmendoza.search;

import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HashMapIndex implements BookGenreIndex {

    private Map<String, List<Book>> index = new HashMap<>();

    public HashMapIndex(List<Book> books) {
        for (Book book: books) {
            for (String key: book.getGenres()) {
                if (!index.containsKey(key)) {
                    index.put(key, new ArrayList<>());
                }
                index.get(key).add(book);
            }
        }
    }

    @Override
    public List<Book> search(String genre) {
        return index.get(genre);
    }
}
