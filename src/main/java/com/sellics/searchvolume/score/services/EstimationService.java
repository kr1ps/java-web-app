package com.sellics.searchvolume.score.services;

import com.sellics.searchvolume.score.domain.AmazonSuggestion;
import com.sellics.searchvolume.score.domain.Estimation;
import com.sellics.searchvolume.score.enums.AmazonSuggestionType;
import com.sellics.searchvolume.score.seo.services.AmazonSEOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mairelin
 * @see IEstimationService
 */
@Service
public class EstimationService implements IEstimationService {

    private final AmazonSEOService service;
    private final Logger logger = LoggerFactory.getLogger(EstimationService.class);

    public EstimationService(AmazonSEOService service) {
        this.service = service;
    }

    @Override
    public Estimation getEstimation(String keyWord) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("prefix", keyWord);
        List<AmazonSuggestion> amazonSuggestions = service.getSuggestions(keyWord, params);
        return getEstimation(amazonSuggestions, keyWord);
    }

    /**
     * @author Mairelin
     * Take a list of suggestions, compare each with the received keyword and return an estimation score.
     * @param amazonSuggestionList suggestions returned by amazon.
     * @param keyWord to research
     * @return Estimation with the final score
     */
    public Estimation getEstimation(List<AmazonSuggestion> amazonSuggestionList, String keyWord) {

        Pattern related =  Pattern.compile(keyWord + ".*", Pattern.CASE_INSENSITIVE);
        Pattern exact =  Pattern.compile(keyWord, Pattern.CASE_INSENSITIVE);
        int keyWordLength = keyWord.length();
        int highestMatch = 0;

        for (AmazonSuggestion amazonSuggestion : amazonSuggestionList) {
            if(amazonSuggestion.getType().equals(AmazonSuggestionType.KEYWORD.toString())) {

                String value = amazonSuggestion.getValue();

                logger.info("Calculating suggestion match --> " + value);

                Matcher relatedMatch = related.matcher(value);
                Matcher exactMatch = exact.matcher(value);

                if(exactMatch.matches()) return  new Estimation(keyWord, 100);

                boolean match = relatedMatch.matches();
                if (match) {
                    int percentage = (100 * keyWordLength) / value.length();
                    highestMatch = percentage > highestMatch ? percentage : highestMatch;

                    logger.info( value + " --> " + highestMatch + "%");
                }
            }
        }
        return new Estimation(keyWord, highestMatch);
    }


}
