package edu.unicen.exa.tudai.prog3.gentilmendoza.search;

public enum Index {
    ARRAYLIST_NODE("ArraylistNode"),
    HASHMAP       ("Hashmap      "),
    ARRAYLIST     ("Arraylist    ");

    private String label;

    Index(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
