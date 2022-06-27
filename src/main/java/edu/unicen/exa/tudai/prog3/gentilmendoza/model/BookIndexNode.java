package edu.unicen.exa.tudai.prog3.gentilmendoza.model;

import java.util.ArrayList;
import java.util.List;

public class BookIndexNode {

    private String key;
    private List<Book> values = new ArrayList<>();

    public BookIndexNode(String key, Book value) {
        this.key = key;
        this.values.add(value);
    }

    public String getKey() {
        return key;
    }

    public List<Book> getValues() {
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BookIndexNode that = (BookIndexNode) o;

        return key.equals(that.key);
    }
}
