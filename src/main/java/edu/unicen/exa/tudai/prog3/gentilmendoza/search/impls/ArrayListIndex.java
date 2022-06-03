package edu.unicen.exa.tudai.prog3.gentilmendoza.search.impls;

import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Book;
import edu.unicen.exa.tudai.prog3.gentilmendoza.search.BookGenreIndex;

import java.util.ArrayList;
import java.util.List;

public class ArrayListIndex implements BookGenreIndex {

    private List<String> genres = new ArrayList<>();
    private List<List<Book>> booksByGenre = new ArrayList<>();

    public ArrayListIndex(List<Book> books) {
        for (Book book : books) {
            for (String genre: book.getGenres()) {
                int index = genres.indexOf(genre);
                if (index == -1) {
                    genres.add(genre);
                    booksByGenre.add(new ArrayList<>());
                    index = genres.size() - 1;
                }
                booksByGenre.get(index).add(book);
            }
        }
    }

    @Override
    public List<Book> search(String genre) {
        int index = genres.indexOf(genre);
        return index != -1 ? booksByGenre.get(index) : new ArrayList<>();
    }
}
