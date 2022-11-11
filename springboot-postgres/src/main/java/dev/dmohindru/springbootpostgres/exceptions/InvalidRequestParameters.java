package dev.dmohindru.springbootpostgres.exceptions;

public class InvalidRequestParameters extends RuntimeException{
    private String message;

    public InvalidRequestParameters(String message) {
        super(message);
        this.message = message;
    }
}
