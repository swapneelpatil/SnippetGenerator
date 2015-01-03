package com.hack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class FixedWordLengthSnippetGenerator implements SnippetGenerator {
    private final int width;

    public FixedWordLengthSnippetGenerator(final int width) {
        this.width = width;
    }

    public Iterator<Snippet> getIterator(final Review review) {
        final List<String> wordSequence = Arrays.asList(review.getText().split(" "));

        return new Iterator<Snippet>() {
            int current = 0;

            @Override
            public boolean hasNext() {
                return (current + width) < wordSequence.size();
            }

            @Override
            public Snippet next() {
                StringBuffer buffer = new StringBuffer();
                if ((current + width) < wordSequence.size()) {
                    for (int i = current; i < current + width; ++i)
                        buffer.append(wordSequence.get(i)).append(" ");
                    ++current;
                    return new Snippet(buffer.toString());
                }
                throw new NoSuchElementException();

            }

            /**
             * This iterator doesn't really need to support the remove operation
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove operation not supported for this iterator");
            }
        };
    }
}
