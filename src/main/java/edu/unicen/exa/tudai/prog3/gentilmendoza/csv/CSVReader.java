package edu.unicen.exa.tudai.prog3.gentilmendoza.csv;

import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    public static void main(String[] args) {
        String csvFile = "[PATH-AL-ARCHIVO]/dataset.csv";
        String line = "";
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] items = line.split(cvsSplitBy);

                Book book = new Book()

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
