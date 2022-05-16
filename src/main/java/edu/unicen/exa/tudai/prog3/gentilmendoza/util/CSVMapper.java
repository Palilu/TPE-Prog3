package edu.unicen.exa.tudai.prog3.gentilmendoza.util;

import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVMapper {

    private static final String HEADERS = "Titulo,Autor,Paginas,Generos";
    private static final String COLUMN_SEPARATOR = ",";
    private static final String GENRE_SEPARATOR = " ";

    public List<Book> readCSVFile(String filename) throws IOException {
        String line;
        List<Book> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        while ((line = br.readLine()) != null) {
            if (line.equalsIgnoreCase(HEADERS)) {
                continue;
            }
            String[] columns = line.split(COLUMN_SEPARATOR);
            Book book = new Book(columns[0], columns[1], Integer.valueOf(columns[2]), getGenres(columns[3]));
            result.add(book);
        }
        return result;
    }

    public void writeCSVFile(String filename, List<Book> books) throws IOException {
        BufferedWriter bw = null;
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(HEADERS);
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
        List<String> result = new ArrayList<>();
        for (String genre: genres.split(GENRE_SEPARATOR)) {
            result.add(genre);
        }
        return result;
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
