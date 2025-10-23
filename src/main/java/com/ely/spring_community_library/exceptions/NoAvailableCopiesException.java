package com.ely.spring_community_library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NoAvailableCopiesException extends RuntimeException {
    public NoAvailableCopiesException(String message) {
        super(message);
    }
}
