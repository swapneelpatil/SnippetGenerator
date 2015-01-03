package com.hack;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class DocumentTest {
    private Document document;
    private String text;

    @Test
    public void testEmptyDocument() {
        document = new Document("");
        Map<String, Integer> expected = new HashMap<String, Integer>();
        expected.put("term", 0);

        assertEquals(0, document.getWordSize().intValue());
        assertEquals("", document.getText());
        assertEquals(0, document.getTermFrequency("term"));
        assertEquals(expected, document.getTermFrequency(Collections.singletonList("term")));
    }

    @Test
    public void testNullDocument() {
        document = new Document(null);
        Map<String, Integer> expected = new HashMap<String, Integer>();
        expected.put("term", 0);

        assertEquals(0, document.getWordSize().intValue());
        assertEquals("", document.getText());
        assertEquals(0, document.getTermFrequency("term"));
        assertEquals(expected, document.getTermFrequency(Collections.singletonList("term")));
    }

    @Test
    public void testFullDocument() {
        text = "this is a test, this is a test";
        document = new Document(text);
        Map<String, Integer> expected = new HashMap<String, Integer>();
        List<String> searchList = new ArrayList<String>();
        searchList.add("test");
        searchList.add("this");
        expected.put("test", 2);
        expected.put("this", 2);

        assertEquals(8, document.getWordSize().intValue());
        assertEquals(text, document.getText());
        assertEquals(2, document.getTermFrequency("test"));
        assertEquals(expected, document.getTermFrequency(searchList));
    }

    @Test
    public void testHighlight() {
        text = "highlight this highlight this";
        document = new Document(text);

        assertEquals("[[HIGHLIGHT]]highlight[[ENDHIGHLIGHT]] this [[HIGHLIGHT]]highlight[[ENDHIGHLIGHT]] this",
                document.highLight("highlight"));
        // NB: We could highlight this to optimize it such that, if end tag follows the start tag we can merge them
        //     however, keeping it simple/readable for now, since the search terms generally tend to be smaller.
        assertEquals("[[HIGHLIGHT]]highlight this[[ENDHIGHLIGHT]] [[HIGHLIGHT]]highlight this[[ENDHIGHLIGHT]]",
                document.highLight("highlight this"));
    }
}
