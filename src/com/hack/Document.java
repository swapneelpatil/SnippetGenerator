package com.hack;

import javax.print.Doc;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Document is a container for text that serves as the model for Review text components. The goal for this interface
 * is to scale from very simple needs (a plain text) to complex needs of a Review/Snippet, etc. parsing,formatting, maybe
 * support stemming, stop-wording or any other optimization on top of it.
 */
public class Document {
    public static final String START_HIGHLIGHT = "[[HIGHLIGHT]]";
    public static final String END_HIGHLIGHT = "[[ENDHIGHLIGHT]]";
    protected final String text;
    protected final Integer wordSize;

    protected Document(final String text) {
        if (text != null && text.length() > 0) {
            this.text = text;
            wordSize = text.split(" ").length;
        } else {
            wordSize = 0;
            this.text = "";
        }
    }

    /**
     * Case insensitive highlight.
     *
     * @param word to highlight
     * @return String containing the specified words enclosed in [[HIGHLIGHT]] [[ENDHIGHLIGHT]] tags.
     * eg. To highlight word "hello" in "hello world", will return "[[HIGHLIGHT]]hello[[ENDHIGHLIGHT]] world"
     */
    public String highLight(final String word) {
        Pattern pattern = Pattern.compile(word.trim(), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.replaceAll(START_HIGHLIGHT + word + END_HIGHLIGHT);
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }

    /**
     * Get number of words in the document, that are space separated. Can be optimized for parsing non-formatted text
     * in reviews.
     */
    public Integer getWordSize() {
        return wordSize;
    }

    public int getTermFrequency(final String term) {
        Pattern pattern = Pattern.compile(term.trim(), Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        int frequency = 0;
        while (matcher.find()) {
            frequency++;
        }
        return frequency;
    }

    /**
     * this doesn't need to calculated every time, if this operation is called frequently enough then this data can be
     * cached/calculated upfront, updated (in an event the document changes), making this a constant time operation.
     */
    public Map<String, Integer> getTermFrequency(final List<String> terms) {
        Map<String, Integer> frequencyMap = new LinkedHashMap<String, Integer>();
        for(String word: terms) {
            frequencyMap.put(word, getTermFrequency(word));
        }
        return frequencyMap;
    }

    public Snippet highLight(List<String> words) {
        String newText = new String(text);
        for (String word: words) {
            Pattern pattern = Pattern.compile(word.trim(), Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(newText);
            newText = matcher.replaceAll(START_HIGHLIGHT + word + END_HIGHLIGHT);
        }
        return new Snippet(newText);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Document)) return false;

        Document document = (Document) o;

        if (text != null ? !text.equals(document.text) : document.text != null) return false;
        if (wordSize != null ? !wordSize.equals(document.wordSize) : document.wordSize != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (wordSize != null ? wordSize.hashCode() : 0);
        return result;
    }
}
