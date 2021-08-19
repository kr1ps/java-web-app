package com.sellics.searchvolume.score.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Problem trying to communicate with the Amazon API.")
public class AmazonAPIUnavailableException extends RuntimeException {
}
