package dev.dmohindru.springbootpostgres.exceptions;

public class NotFoundExceptions extends RuntimeException {
    private final String message;

    public NotFoundExceptions(String message) {
        super(message);
        this.message = message;

    }
}
