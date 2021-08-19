package com.sellics.searchvolume.score.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Problem trying to read the information from the the API.")
public class ParsingResponseException extends RuntimeException {
}
