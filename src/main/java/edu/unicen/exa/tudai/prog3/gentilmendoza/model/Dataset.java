package edu.unicen.exa.tudai.prog3.gentilmendoza.model;

public enum Dataset {
    D1("dataset1.csv"),
    D2("dataset2.csv"),
    D3("dataset3.csv"),
    D4("dataset4.csv");

    private static final String BOOKS_FOLDER = "src/main/resources/datasets/";
    private static final String SEARCH_FOLDER = "src/main/resources/search/";

    private final String filename;

    Dataset(String filename) {
        this.filename = filename;
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
}
