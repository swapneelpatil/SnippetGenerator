package com.hack;

import java.util.Iterator;

/**
 * Interface that indicates the way in which a Snippet is generated off of a {@link Review}. This can be a
 * fixed word size or fixed sentences, or, even something with stem/stop wording applied.
 */
public interface SnippetGenerator {
    /**
     * Iterator that traverses through all the Snippet's in a {@link Review}.
     *
     * @param review
     * @return {@link Snippet} Iterator
     */
    public Iterator<Snippet> getIterator(final Review review);
}