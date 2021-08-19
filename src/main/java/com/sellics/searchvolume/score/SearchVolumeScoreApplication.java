package com.sellics.searchvolume.score;

import com.sellics.searchvolume.score.properties.MarketPlacesProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Mairelin
 * @version 0.1
 */
@SpringBootApplication
@EnableConfigurationProperties(MarketPlacesProperties.class)
public class SearchVolumeScoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchVolumeScoreApplication.class, args);
    }

}
