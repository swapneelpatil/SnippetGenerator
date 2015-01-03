package com.hack;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by swap on 4/8/14.
 */
public class SnippetTest {
    private Snippet snippet;

    @Test
    public void setGetSnippetTest() {
        snippet = new Snippet("test snippet");
        Double relevance = 1.33;
        snippet.setRelevance(relevance);

        assertEquals(relevance, snippet.getRelevance());
    }

    @Test
    public void testCompareTo() {
        Double higher = 4.5;
        Double lower = 1.44;

        snippet = new Snippet("test");
        snippet.setRelevance(higher);

        Snippet snippet2 = new Snippet("test2");
        snippet2.setRelevance(lower);

        assertEquals(1, higher.compareTo(lower));  // higher
        assertEquals(0, lower.compareTo(lower));   // equals
        assertEquals(-1, lower.compareTo(higher)); // lower
    }
}
