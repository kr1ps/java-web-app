package com.sellics.searchvolume.score.seo.services;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.sellics.searchvolume.score.domain.AmazonSuggestion;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import javax.xml.ws.http.HTTPException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author Mairelin
 * @see <a href="https://mairelintest.stoplight.io/studio/amazon-autocomplete}"> Amazon API doc</a>
 * Implementation of the logic to gather the information from the Amazon API
 */
@Component
public class AmazonSEOService implements ISEOService<AmazonSuggestion> {

    @Autowired
    private Environment env;
    private final Logger logger = LoggerFactory.getLogger(AmazonSEOService.class);

    /**
     * @author Mairelin
     * @param params params to add to the amazon API request.
     * @return List<Suggestion> List of suggestions returned by the consumed autocomplete API
     */
    @Override
    public List<AmazonSuggestion> getSuggestions(String keyWord, Map<String, String> params) throws IOException, HTTPException {
        String path = getURL(env.getProperty("amazon.com.path"), params);
        logger.info("Calling Amazon Autocomplete API: "+path);

        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

         //getting response
        BufferedReader buffered = new BufferedReader(new InputStreamReader((connection.getInputStream())));
        String response = buffered.readLine();
        logger.info("Response: "+response);
        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {

          logger.warn("Amazon API response code "+ connection.getResponseCode());
          throw new HTTPException(connection.getResponseCode());
        }

        //parsing suggestion list
        List<AmazonSuggestion> res = parseSuggestions(response);

        connection.disconnect();
        return res;
    }

    private List<AmazonSuggestion> parseSuggestions(String response) {
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        JsonArray suggestionArray;
        suggestionArray = jsonObject.getAsJsonArray("suggestions");
        Type listType = new TypeToken<List<AmazonSuggestion>>() {}.getType();
        Gson gson = new Gson();
        return gson.fromJson(suggestionArray.toString(), listType);
    }

    /**
     * @author Mairelin
     * @param baseUrl base autocomplete API URL
     * @param parameters query params
     * @return path url
     */
    private String getURL(String baseUrl, Map<String, String> parameters){
        UriComponentsBuilder builder=UriComponentsBuilder.fromUriString(baseUrl);
        for ( Map.Entry<String, String> entry : parameters.entrySet()) {
            builder.queryParam(entry.getKey(), UriUtils.encodePath(entry.getValue(), "UTF-8"));
        }
        return builder.buildAndExpand().toUriString();
    }

}
