package edu.unicen.exa.tudai.prog3.gentilmendoza.collections;

import java.util.Objects;

public class Vertex<T> {

    private T label;

    public Vertex(T label) {
        this.label = label;
    }

    public T getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex<?> vertex = (Vertex<?>) o;

        return Objects.equals(label, vertex.label);
    }

    @Override
    public int hashCode() {
        return label != null ? label.hashCode() : 0;
    }
}
