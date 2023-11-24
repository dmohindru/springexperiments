package dev.dmohindru.StringProcessorServer.exceptions;

public class InvalidAppArgumentExpection extends RuntimeException {
    public InvalidAppArgumentExpection(String msg) {
        super(msg);
    }
}
