package com.sellics.searchvolume.score.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "marketplace")
@PropertySource("classpath:marketplaces.properties")
public class MarketPlacesProperties {
}
