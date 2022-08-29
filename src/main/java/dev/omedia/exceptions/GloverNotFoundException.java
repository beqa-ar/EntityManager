package dev.omedia.exceptions;

public class GloverNotFoundException extends RuntimeException {
    public GloverNotFoundException(long id) {
        super(String.format("Glover not found with id %d", id));
    }
}
