package edu.unicen.exa.tudai.prog3.gentilmendoza.search;

import edu.unicen.exa.tudai.prog3.gentilmendoza.collections.Tree;
import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Book;

import java.util.List;

public class TreeIndex implements BookGenreIndex {

    private Tree tree = new Tree();

    public TreeIndex(List<Book> books) {
        for (Book book: books) {
            tree.insert(book);
        }
    }

    @Override
    public List<Book> search(String genre) {
        return tree.get(genre);
    }
}
