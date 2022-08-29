package dev.omedia.exceptions;

public class RestaurantBranchNotFoundException extends RuntimeException{
    public RestaurantBranchNotFoundException(long id, long restaurantId) {
        super("restaurant with id: "+id+" and with restaurant id :"+restaurantId+" not found");
    }
    }

