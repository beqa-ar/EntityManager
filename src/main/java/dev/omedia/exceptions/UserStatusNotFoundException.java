package dev.omedia.exceptions;

public class UserStatusNotFoundException extends RuntimeException {
    public UserStatusNotFoundException(long id) {
            super(String.format("UserStatus not found with id %d", id));
        }
    }
