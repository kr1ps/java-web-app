package com.sellics.searchvolume.score.enums;

/**
 * @author Mairelin
 * Amazon list suggestions types
 */
public enum AmazonSuggestionType {

    KEYWORD("KEYWORD");

    private String value;

    AmazonSuggestionType(String keyword) {
        value = keyword;
    }

    @Override
    public String toString() {
        return value;
    }
}
