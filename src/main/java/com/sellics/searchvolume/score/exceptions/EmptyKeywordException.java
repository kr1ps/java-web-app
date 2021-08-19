package com.sellics.searchvolume.score.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.PRECONDITION_REQUIRED, reason="Please insert a keyword as param")
public class EmptyKeywordException extends RuntimeException {
}
