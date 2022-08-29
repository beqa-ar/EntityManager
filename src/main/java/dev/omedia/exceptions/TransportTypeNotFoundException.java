package dev.omedia.exceptions;

public class TransportTypeNotFoundException extends RuntimeException{
    public TransportTypeNotFoundException(long id) {
        super(String.format("TransportType not found with id %d", id));
    }
}
