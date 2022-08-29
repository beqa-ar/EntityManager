package dev.omedia.exceptions;

public class PageNotFoundException extends RuntimeException {
    public PageNotFoundException(long page) {
        super(String.format("page not found with id %d", page));
    }
}
