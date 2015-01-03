package com.hack;

/**
 * A snippet is essentially a entire/part of a document, with relevance value attached to it.
 */
public class Snippet extends Document implements Comparable<Snippet> {
    private Double relevance;

    protected Snippet(String text) {
        super(text);
    }

    public Double getRelevance() {
        return relevance;
    }

    public void setRelevance(final Double relevance) {
        this.relevance = relevance;
    }

    @Override
    public int compareTo(final Snippet weight) {
        return -1 * relevance.compareTo(weight.getRelevance()); // document with highest relevance ordered first.
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Snippet)) return false;

        Snippet snippet = (Snippet) o;

        if (relevance != null ? !relevance.equals(snippet.relevance) : snippet.relevance != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return relevance != null ? relevance.hashCode() : 0;
    }
}
