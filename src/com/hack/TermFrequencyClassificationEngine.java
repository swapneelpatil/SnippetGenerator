package com.hack;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TermFrequencyClassificationEngine implements ClassificationEngine {

    final SnippetGenerator generator;

    public TermFrequencyClassificationEngine(final SnippetGenerator generator) {
        this.generator = generator;
    }

    @Override
    public Snippet getDisplaySnippet(final Review review, final String searchString) {

        List<String> words = new ArrayList<String>();
        for(String word: searchString.split(" ")) {
            words.add(word.trim());
        }

        Iterator<Snippet> snippets = generator.getIterator(review);

        /**
         * More information, and motivation see http://en.wikipedia.org/wiki/Tf%E2%80%93idf
         *
         * Get the term frequency, for the search terms inside the document, and calculate how much relevant they are
         * inside the document. eg. if the Review is "deep deep deep dish pizza pizza pizza", and search string is
         * "deep dish pizza", the words "pizza" and "deep", occur more frequently in the document than the word dish
         * i.e. these words bias the document, hence they carry a lower weight
         *
         * Their relevancy weight is calculated by following formula
         *
         * relevancy(word) =  document-size / frequency(word)^2
         *
         * Hence, higher the frequency lower is the relevant weight.
         */
        Map<String,Integer> docFrequency = review.getTermFrequency(words);
        Map<String,Double> docTF = new HashMap<String, Double>();

        for (Map.Entry<String, Integer> tf: docFrequency.entrySet()) {
            docTF.put(tf.getKey(), review.getWordSize().doubleValue() / (tf.getValue() * tf.getValue()));
        }

        PriorityQueue<Snippet> sorted = new PriorityQueue<Snippet>(1);

        /**
         * Goodness/weight of the snippet is then,
         *
         * weight(snippet) = For all words SUM[
         *              frequency (word) in snippet / relevancy (word), if frequency > 0
         *              - relevancy(word)                             , if frequency == 0
         *              ]
         *
         * Hence, if the relevancy of words in the snippet is higher in perspective of the document, the snippet is
         * considered as relevant. This engine then returns the snippet with highest relevancy.
         */
        while (snippets.hasNext()) {
            Snippet snippet = snippets.next();
            Map<String, Integer> snippetTF = snippet.getTermFrequency(words);
            double relevance = 0.0;
            for (Map.Entry<String, Integer> entry: snippetTF.entrySet()) {
                Integer freq = entry.getValue();
                String term = entry.getKey();
                double weight = docTF.get(term);
                if (freq != 0) {
                    relevance += freq * weight;
                } else {
                    relevance -= weight;
                }
            }

            snippet.setRelevance(relevance);
            sorted.add(snippet);
        }

        return sorted.peek().highLight(words);
    }
}
