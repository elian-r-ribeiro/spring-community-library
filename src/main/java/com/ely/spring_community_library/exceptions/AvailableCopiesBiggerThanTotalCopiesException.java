package com.ely.spring_community_library.exceptions;

public class AvailableCopiesBiggerThanTotalCopiesException extends RuntimeException {
    public AvailableCopiesBiggerThanTotalCopiesException(String message) {
        super(message);
    }
}
