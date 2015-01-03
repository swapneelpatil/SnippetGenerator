package com.hack;

public interface ClassificationEngine {
    /**
     * Return the most relevant Snippet from the review, based on the search string. Anyone implementing the interface
     * is free to use any {@link com.hack.SnippetGenerator} or build their own, for performance/optimization reasons.
     *
     * You can also combine multiple {@link com.hack.ClassificationEngine} to build a metasearch, say one using the term
     * frequency other leveraging some other model and base the final result a ranked result of the combination.
     *
     * @param review to find relevant snippet off of
     * @param searchString
     * @return Relevant {@link Snippet} per the classification engine.
     */
    Snippet getDisplaySnippet(Review review, String searchString);
}
