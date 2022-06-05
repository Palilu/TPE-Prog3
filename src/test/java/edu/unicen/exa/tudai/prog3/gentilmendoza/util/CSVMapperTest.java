package edu.unicen.exa.tudai.prog3.gentilmendoza.util;

import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Book;

import java.util.List;

public class CSVMapperTest {

    private static CSVMapper mapper = new CSVMapperImpl();

    public static void main(String[] args) {
        String filename = "src/main/resources/datasets/dataset1.csv";
        // Test readBooksCSVFile
        mapper.readBooksCSVFile(filename).forEach(System.out::println);
        // Test writeBooksCSVFile
        Book tropicOfCancer = new Book("Tropic of Cancer", "Henry Miller", 567, List.of("Fiction"));
        Book tropicOfCapricorn = new Book("Tropic of Capricorn", "Henry Miller", 789, List.of("Fiction"));
        mapper.writeCSVFile("src/main/resources/datasets/dataset5.csv", List.of(tropicOfCancer, tropicOfCapricorn));
        // Test readSearchCSVFile
        filename = "src/main/resources/searchs/dataset1.csv";
        System.out.println(mapper.readSearchCSVFile(filename));
    }


}
