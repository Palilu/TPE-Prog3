package edu.unicen.exa.tudai.prog3.gentilmendoza.csv;

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

    public void writeCSVFile(List<Book> books) throws IOException {
//        BufferedWriter bw = null;
//        try {
//            File file = new File("[PATH-AL-ARCHIVO]/salida.csv");
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//
//            FileWriter fw = new FileWriter(file);
//            bw = new BufferedWriter(fw);
//
//            for (Book book: books) {
//                bw.write();
//                bw.newLine();
//            }
//            // Escribo la primer linea del archivo
//
//
//            // Escribo la segunda linea del archivo
//            String contenidoLinea2 = "Usuario2;Tiempo2";
//            bw.write(contenidoLinea2);
//            bw.newLine();
//
//            /*
//             *
//             * ...
//             *
//             */
//
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        } finally {
//            try {
//                if (bw != null)
//                    bw.close();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
    }

    private List<String> getGenres(String genres) {
        List<String> result = new ArrayList<>();
        for (String genre: genres.split(GENRE_SEPARATOR)) {
            result.add(genre);
        }
        return result;
    }

    private String toCSV(Book book) {
        StringBuilder result = new StringBuilder();
        result.append(book.getTitle());
        result.append(COLUMN_SEPARATOR);
        result.append(book.getAuthor());
        result.append(COLUMN_SEPARATOR);
        result.append(book.getPages());
        result.append(COLUMN_SEPARATOR);
        result.append(toCSV(book.getGenres()));
        result.append(COLUMN_SEPARATOR);
        return result.toString();
    }

    private String toCSV(List<String> genres) {
        StringBuilder result = new StringBuilder();
        for (String genre: genres) {
            result.append(genre);
            result.append(GENRE_SEPARATOR);
        }
        return result.toString();
    }
}
