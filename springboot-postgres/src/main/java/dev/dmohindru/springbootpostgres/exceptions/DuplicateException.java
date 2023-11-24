package dev.dmohindru.springbootpostgres.exceptions;

public class DuplicateException extends RuntimeException{

    private final String message;

    public DuplicateException(String message) {
        super(message);
        this.message = message;
    }
}
