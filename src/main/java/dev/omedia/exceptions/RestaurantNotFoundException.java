package dev.omedia.exceptions;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(long id) {
        super(String.format("Restaurant not found with id %d", id));
    }
}
