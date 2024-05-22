package com.lnrs.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CompanySearchException extends RuntimeException {
    public CompanySearchException(String message) {
        super(message);
    }
}
