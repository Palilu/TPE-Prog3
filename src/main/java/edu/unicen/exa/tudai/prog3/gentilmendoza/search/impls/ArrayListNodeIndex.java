package edu.unicen.exa.tudai.prog3.gentilmendoza.search.impls;

import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Book;
import edu.unicen.exa.tudai.prog3.gentilmendoza.model.BookIndexNode;
import edu.unicen.exa.tudai.prog3.gentilmendoza.search.BookGenreIndex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ArrayListNodeIndex implements BookGenreIndex {

    private List<BookIndexNode> listIndex = new ArrayList<>();

    public ArrayListNodeIndex(List<Book> books) {
        for (Book book : books) {
            for (String genre: book.getGenres()) {
                Optional<Integer> indexOpt = this.getIndexOf(genre);
                if (indexOpt.isEmpty()) {
                    listIndex.add(new BookIndexNode(genre, book));
                } else {
                    listIndex.get(indexOpt.get()).getValues().add(book);
                }
            }
        }
    }

    private Optional<Integer> getIndexOf(String genre) {
        for (int i = 0; i < listIndex.size(); i++) {
            if (genre.equalsIgnoreCase(listIndex.get(i).getKey())) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Book> search(String genre) {
        Optional<Integer> indexOpt = this.getIndexOf(genre);
        return indexOpt.isEmpty() ? Collections.emptyList() : listIndex.get(indexOpt.get()).getValues();
    }
}
