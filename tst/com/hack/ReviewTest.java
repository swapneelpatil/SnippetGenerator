package com.hack;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReviewTest {
    @Test
    public void testConstructorAndGetText() {
        assertEquals("hello world", new Review("hello world").getText());
    }
}
