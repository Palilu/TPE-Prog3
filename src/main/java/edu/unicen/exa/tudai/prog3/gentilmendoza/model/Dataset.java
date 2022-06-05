package edu.unicen.exa.tudai.prog3.gentilmendoza.model;

public enum Dataset {
    D0("dataset0.csv", 0),
    D1("dataset1.csv", 20), // Porque no 10? -.-!
    D2("dataset2.csv", 1000),
    D3("dataset3.csv", 100000),
    D4("dataset4.csv", 1000000);

    private static final String BOOKS_FOLDER = "src/main/resources/datasets/";
    private static final String SEARCH_FOLDER = "src/main/resources/search/";

    private final String filename;

    private final Integer size;

    Dataset(String filename, Integer size) {
        this.filename = filename;
        this.size = size;
    }

    public String getBookPath() {
        return BOOKS_FOLDER + filename;
    }

    public String getSearchPath() {
        return SEARCH_FOLDER + filename;
    }

    public String getFilename() {
        return filename;
    }

    public Integer getSize() {
        return size;
    }
}
