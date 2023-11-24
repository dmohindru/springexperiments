package dev.dmohindru.StringProcessorServer.exceptions;

public class MissingAppArgumentException extends RuntimeException {
    public MissingAppArgumentException(String msg) {
        super(msg);
    }
}
