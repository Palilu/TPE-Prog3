package edu.unicen.exa.tudai.prog3.gentilmendoza.main;

import edu.unicen.exa.tudai.prog3.gentilmendoza.collections.Graph;
import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Book;
import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Dataset;
import edu.unicen.exa.tudai.prog3.gentilmendoza.util.CSVMapper;
import edu.unicen.exa.tudai.prog3.gentilmendoza.util.CSVMapperImpl;

import java.util.List;

public class GraphService {

    private CSVMapper csvMapper = new CSVMapperImpl();

    public void generateGraphAndRunServices(Dataset dataset) {
        // Leemos el CSV
        List<List<String>> searchs = csvMapper.readSearchCSVFile(dataset.getSearchPath());
        // Creamos el gráfo
        Graph<String> genreSearchGraph = new Graph<>();
        // Cargamos las búsquedas en el grafo
        searchs.forEach(search -> {
            for (int i = 1; i < search.size(); i++) {
                genreSearchGraph.addEdge(search.get(i-1), search.get(i));
            }
        });
        System.out.println(genreSearchGraph.exportForGraphvizOnline(" G "));

    }
}
