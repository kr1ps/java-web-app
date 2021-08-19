package com.sellics.searchvolume.score.controllers;

import com.sellics.searchvolume.score.domain.Estimation;
import com.sellics.searchvolume.score.exceptions.AmazonAPIUnavailableException;
import com.sellics.searchvolume.score.exceptions.EmptyKeywordException;
import com.sellics.searchvolume.score.exceptions.ParsingResponseException;
import com.sellics.searchvolume.score.services.EstimationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;

/**
 * @author Mairelin
 * Estimation controller
 * */
@RestController
public class EstimationController {

    private Logger logger = LoggerFactory.getLogger(EstimationController.class);

    private EstimationService service;

    public EstimationController(EstimationService service) {
        this.service = service;
    }

    @GetMapping("estimation/{keyword}")
    @ExceptionHandler({ AmazonAPIUnavailableException.class, ParsingResponseException.class, ParsingResponseException.class})
    public Estimation getEstimation(@PathVariable String keyword) {

        if(keyword == null || keyword.isEmpty()) {
            throw new EmptyKeywordException();
        }

        logger.info("Consulting keyword " + keyword);

        Estimation estimation = new Estimation("keyword", -1);
        try {
            estimation = service.getEstimation(keyword);

        } catch (IOException e) {
           logger.error(e.getMessage());
           throw new ParsingResponseException();
        } catch (HTTPException e) {
            logger.error(e.getMessage());
           throw new AmazonAPIUnavailableException();
        }

        logger.info("Finishing request for " + keyword);
        return estimation;

    }


}
