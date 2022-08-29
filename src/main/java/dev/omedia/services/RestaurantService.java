package dev.omedia.services;


import dev.omedia.domains.Restaurant;
import dev.omedia.exceptions.RestaurantNotFoundException;
import dev.omedia.repositoreis.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepo repo;

    @Autowired
    public RestaurantService(RestaurantRepo repo) {
        this.repo = repo;
    }

    public List<Restaurant> getRestaurants() {
        return repo.findAll();
    }

    public Restaurant getRestaurant(final long id) {
        return repo.findById(id).orElseThrow(()-> new RestaurantNotFoundException(id));
    }

    public boolean addRestaurant(final Restaurant restaurant) {
        repo.save(restaurant);
        return true;
    }

    public Restaurant updateRestaurant(final long id, final Restaurant restaurant) {
        restaurant.setId(id);
        return repo.update(restaurant);
    }

    public boolean removeRestaurant(final long id) {
        repo.remove(id);
        return true;
    }
}
