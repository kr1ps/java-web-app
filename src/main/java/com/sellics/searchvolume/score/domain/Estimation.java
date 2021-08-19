package com.sellics.searchvolume.score.domain;

/**
 * @author Mairelin
 * This domain represents the estimation for the keywords
 */
public class Estimation {
    private String keyWord;
    private Integer score;

    public Estimation(String keyWord, Integer score) {
        this.keyWord = keyWord;
        this.score = score;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * @value integer from 0...100
     * This represent the estimation score value of the input keyword
     */
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
