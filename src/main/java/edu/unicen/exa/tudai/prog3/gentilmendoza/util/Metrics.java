package edu.unicen.exa.tudai.prog3.gentilmendoza.util;

import edu.unicen.exa.tudai.prog3.gentilmendoza.model.Dataset;
import edu.unicen.exa.tudai.prog3.gentilmendoza.search.Index;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Metrics {

    private class Metric {

        private Dataset dataset;
        private Index index;
        private String message;
        private Double time;

        public Metric(Dataset dataset, Index index, String message, Double time) {
            this.dataset = dataset;
            this.index = index;
            this.message = message;
            this.time = time;
        }

        public Dataset getDataset() {
            return dataset;
        }

        public Index getIndex() {
            return index;
        }

        public String getMessage() {
            return message;
        }

        public Double getTime() {
            return time;
        }
    }

    private Timer timer = new Timer();

    List<Metric> metrics = new ArrayList<>();

    public Metrics() {
        timer.start();
    }

    public void addMetric(Dataset dataset, Index index, String message) {
        metrics.add(new Metric(dataset, index, message, timer.stop()));
        timer.start();
    }

    public void analyze() {
        Map<String, List<Metric>> metricsByMessage = metrics.stream().collect(Collectors.groupingBy(Metric::getMessage));
        System.out.println("Analizando métricas TPE programación 3.");
        System.out.println("Analizando parseo del archivo");
        printAverages(metricsByMessage, "fileParsing");
        System.out.println("Analizando la creación del índice");
        printAverages(metricsByMessage, "indexCreation");
        System.out.println("Analizando la búsqueda");
        printAverages(metricsByMessage, "searchByGenre");
        System.out.println("Analizando la escritura del archivo");
        printAverages(metricsByMessage, "fileCreation");
    }

    private void printAverages(Map<String, List<Metric>> metricsByMessage, String message) {
        metricsByMessage.get(message).stream()
                .collect(Collectors.groupingBy(Metric::getDataset))
                .entrySet()
                .forEach(entry -> entry.getValue().stream()
                        .collect(Collectors.groupingBy(Metric::getIndex))
                        .entrySet()
                        .forEach(indexEntry -> {
                                System.out.print("Dataset: ");
                                System.out.print(entry.getKey().getFilename());
                                System.out.print(" Index: ");
                                System.out.print(indexEntry.getKey());
                                System.out.print(" AVG: ");
                                Double average = indexEntry.getValue().stream().mapToDouble(Metric::getTime).average().getAsDouble();
                                System.out.print(average);
                                System.out.println(".");
                            }));
    }
}
