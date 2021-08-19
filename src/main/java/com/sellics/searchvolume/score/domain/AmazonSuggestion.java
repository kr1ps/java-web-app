package com.sellics.searchvolume.score.domain;


/**
 * @author Mairelin
 * Suggestion returned by an autocomplete suggestion list.
 */
public class AmazonSuggestion {

    private String type;

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
