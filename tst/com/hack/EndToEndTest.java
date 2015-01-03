package com.hack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by swap on 4/8/14.
 */
public class EndToEndTest {
    private TermFrequencyClassificationEngine engine;
    private FixedWordLengthSnippetGenerator snippetGenerator;
    private Review review;

    private final String reviewText = "I would have to agree with most of the review here. this place has one of the best" +
            " pizzas I ever had. We had the classic deep dish pizza with sausage cheese, and tomatoes. It was perfectly " +
            "cooked down to the crust. Deep dish pizza is a must. On the other hand. The chicken wing appetizer is plain " +
            "so it is ok to skip it. This place gets crowded for dinner so make sure you have your party before you come " +
            "or else you will have to wait.";
    private final String search = "deep dish pizza";


    public EndToEndTest() {
        // we're interested in snippets of exactly 40 words. however, the design of the engine is
        // flexible such that you can plug in any custom snippet generator. all it needs to do is
        // implement the SnippetGenerator interface.
        snippetGenerator = new FixedWordLengthSnippetGenerator(40);
        engine = new TermFrequencyClassificationEngine(snippetGenerator);
        review = new Review(reviewText);
    }

    public static void main(String... args) {
        EndToEndTest e = new EndToEndTest();
        System.out.println(e.engine.getDisplaySnippet(e.review, e.search).getText());
    }


}
