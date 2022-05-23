package edu.unicen.exa.tudai.prog3.gentilmendoza.search;

public enum Index {
    HASHMAP       ("Hashmap      "),
    ARRAYLIST_NODE("ArraylistNode"),
    ARRAYLIST     ("Arraylist    "),
    TREE          ("Tree         ");

    private String label;

    Index(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
