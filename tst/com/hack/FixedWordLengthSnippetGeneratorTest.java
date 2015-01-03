package com.hack;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class FixedWordLengthSnippetGeneratorTest {
    FixedWordLengthSnippetGenerator generator;

    @Before
    public void setup() {
        generator = new FixedWordLengthSnippetGenerator(3);
    }

    @Test( expected = UnsupportedOperationException.class)
    public void iteratorRemoveTest() {
        generator.getIterator(new Review("Tester")).remove();
    }

    @Test (expected = NoSuchElementException.class)
    public void iteratorSnippetsTest() {
        Snippet[] expected = new Snippet[] {
                new Snippet("This is just "),
                new Snippet("is just great, "),
                new Snippet("just great, isn't "),
                new Snippet("great, isn't it")};

        Iterator<Snippet> iterator = generator.getIterator(new Review("This is just great, isn't it"));
        int index = 0;
        while (iterator.hasNext()) {
            assertEquals(expected[index], iterator.next()); // happy case :-)
        }

        assertEquals(false, iterator.hasNext()); // iterator should say, no more elements

        iterator.next(); // this next should throw a NoSuchElementException
    }
}
