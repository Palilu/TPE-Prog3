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
        // Parsear el archivo no depende del índice, es O(l) donde l es la cantidad de líneas en un dataset.
        metricsByMessage.get("fileParsing").stream()
                .collect(Collectors.groupingBy(Metric::getDataset))
                .entrySet()
                        .forEach(entry -> {
                            System.out.print("Dataset: ");
                            System.out.print(entry.getKey().getFilename());
                            System.out.print(" AVG: ");
                            Double average = entry.getValue().stream().mapToDouble(Metric::getTime).average().getAsDouble();
                            System.out.print(average);
                            System.out.println(".");
                        });
//        metricsByMessage.entrySet().forEach(entry -> {
//            System.out.println("Analizando " + entry.getKey());
//            Double average = entry.getValue().stream().mapToDouble(Metric::getTime).average().getAsDouble();
//            System.out.println("Tiempo promedio: " + average);
//        });

    }
}
