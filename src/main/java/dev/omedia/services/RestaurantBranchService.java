package dev.omedia.services;


import dev.omedia.domains.RestaurantBranch;
import dev.omedia.exceptions.RestaurantBranchNotFoundException;
import dev.omedia.repositoreis.RestaurantBranchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantBranchService {
    private final RestaurantBranchRepo repo;

    @Autowired
    public RestaurantBranchService(RestaurantBranchRepo repo) {
        this.repo = repo;
    }

    public List<RestaurantBranch> getRestaurantBranches(final long restaurantId) {
        return repo.findByRestaurant(restaurantId);
    }

    public RestaurantBranch getRestaurantBranch(final long id,final long restaurantId ) {
        return repo.findById(id,restaurantId).orElseThrow(()-> new RestaurantBranchNotFoundException(id,restaurantId));
    }

    public boolean addRestaurantBranch(final RestaurantBranch restaurantBranch) {
        repo.save(restaurantBranch);
        return true;
    }

    public RestaurantBranch updateRestaurantBranch(final long id,final long restaurantId , final RestaurantBranch restaurantBranch) {
        restaurantBranch.setId(id);
        restaurantBranch.setRestaurantId(restaurantId);
        return repo.update(restaurantBranch);
    }

    public boolean removeRestaurantBranch(final long id,final long restaurantId) {
        repo.remove(id,restaurantId);
        return true;
    }
}
