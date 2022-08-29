package dev.omedia.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(long id){
        super(String.format("User not found with id %d", id));
    }
}
