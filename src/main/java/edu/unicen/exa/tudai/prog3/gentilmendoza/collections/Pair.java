package edu.unicen.exa.tudai.prog3.gentilmendoza.collections;

/**
 * @param <L> Left type.
 * @param <R> Right type.
 */
public class Pair<L, R> {

    private L left;

    private R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "(" + left + ", " + right + ')';
    }
}
