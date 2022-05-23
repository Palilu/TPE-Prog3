package edu.unicen.exa.tudai.prog3.gentilmendoza.model;

public enum Dataset {
    D0("dataset0.csv", 0),
    D1("dataset1.csv", 20), // Porque no 10? -.-!
    D2("dataset2.csv", 1000),
    D3("dataset3.csv", 100000),
    D4("dataset4.csv", 1000000);

    private static final String FOLDER = "";

    private String filename;

    private Integer size;

    Dataset(String filename, Integer size) {
        this.filename = filename;
        this.size = size;
    }

    public String getPath() {
        return FOLDER + filename;
    }

    public String getFilename() {
        return filename;
    }

    public Integer getSize() {
        return size;
    }
}
