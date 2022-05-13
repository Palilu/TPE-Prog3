package edu.unicen.exa.tudai.prog3.gentilmendoza.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un libro.
 */
public class Book {

    private String title;
    private String author;
    private Integer pages;
    private List<String> genres = new ArrayList<>();

    public Book(String title, String author, Integer pages, List<String> genres) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.genres = genres;
    }

    public boolean belongs(String genre) {
        return genres.contains(genre);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getPages() {
        return pages;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}

