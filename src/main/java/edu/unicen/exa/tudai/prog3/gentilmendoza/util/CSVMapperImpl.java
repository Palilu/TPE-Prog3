package edu.unicen.exa.tudai.prog3.gentilmendoza.util;

import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CSVMapperImpl implements CSVMapper {

    private static final String BOOK_FILE_HEADERS = "Titulo,Autor,Paginas,Generos";
    private static final String SEARCH_FILE_HEADER = "Generos";

    private static final String COLUMN_SEPARATOR = ",";
    private static final String GENRE_SEPARATOR = " ";

    public List<Book> readBooksCSVFile(String filename) {
        try {
            String line;
            List<Book> result = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                try {
                    String[] columns = line.split(COLUMN_SEPARATOR);
                    Book book = new Book(columns[0], columns[1], Integer.valueOf(columns[2]), getGenres(columns[3]));
                    result.add(book);
                } catch (NumberFormatException e) {
                    // Headers
                }
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public List<List<String>> readSearchCSVFile(String filename) {
        try {
            String line;
            List<List<String>> result = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                var split = line.split(COLUMN_SEPARATOR);
                if (split.length > 1) {
                    result.add(new ArrayList<>(Arrays.asList(split)));
                }
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public void writeCSVFile(String filename, List<Book> books) {
        BufferedWriter bw = null;
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(BOOK_FILE_HEADERS);
            bw.newLine();
            for (Book book: books) {
                bw.write(toCSV(book));
                bw.newLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private List<String> getGenres(String genres) {
        return new ArrayList<>(Arrays.asList(genres.split(GENRE_SEPARATOR)));
    }

    private String toCSV(Book book) {
        StringBuilder builder = new StringBuilder();
        builder.append(book.getTitle());
        builder.append(COLUMN_SEPARATOR);
        builder.append(book.getAuthor());
        builder.append(COLUMN_SEPARATOR);
        builder.append(book.getPages());
        builder.append(COLUMN_SEPARATOR);
        builder.append(toCSV(book.getGenres()));
        builder.append(COLUMN_SEPARATOR);
        String result = builder.toString();
        return result.substring(0, result.length() - 1);
    }

    private String toCSV(List<String> genres) {
        StringBuilder builder = new StringBuilder();
        for (String genre: genres) {
            builder.append(genre);
            builder.append(GENRE_SEPARATOR);
        }
        String result = builder.toString();
        return result.substring(0, result.length() - 1);
    }
}
