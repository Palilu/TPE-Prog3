package edu.unicen.exa.tudai.prog3.gentilmendoza.search;

import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Book;
import edu.unicen.exa.tudai.prog3.gentilmendoza.search.impls.ArrayListIndex;
import edu.unicen.exa.tudai.prog3.gentilmendoza.search.impls.ArrayListNodeIndex;
import edu.unicen.exa.tudai.prog3.gentilmendoza.search.impls.HashMapIndex;
import edu.unicen.exa.tudai.prog3.gentilmendoza.search.impls.TreeIndex;

import java.util.List;

public class BookGenreIndexFactory {

    public BookGenreIndex getBookGenreIndex(Index index, List<Book> books) {
        switch (index) {
            case HASHMAP: return new HashMapIndex(books);
            case ARRAYLIST: return new ArrayListIndex(books);
            case ARRAYLIST_NODE: return new ArrayListNodeIndex(books);
            case TREE: return new TreeIndex(books);
        }
        return null;
    }
}
