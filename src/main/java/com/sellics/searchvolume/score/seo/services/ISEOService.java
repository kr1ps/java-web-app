package com.sellics.searchvolume.score.seo.services;


import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Mairelin
 * Interface to handle the suggestion list.
 */
public interface ISEOService<T> {

    /**
     * @author Mairelin
     * @param keyWord would to research
     * @param params list of query params to tbe attached to the autocomplete API request.
     * @return List of suggestions returned by the consumed autocomplete API, to be evaluated for the calculation of the score.
     * @throws IOException
     * @throws InterruptedException
     */
    List<T> getSuggestions(String keyWord, Map<String, String> params) throws IOException, InterruptedException;
}
