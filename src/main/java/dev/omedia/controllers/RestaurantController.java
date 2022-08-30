package dev.omedia.controllers;

import dev.omedia.domains.Restaurant;
import dev.omedia.helpers.Pager;
import dev.omedia.services.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("Restaurants")
public class RestaurantController {
    private final RestaurantService service;

    @Autowired
    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Collection<Restaurant>> getRestaurants(
            @RequestParam(required = false,defaultValue = "${page}", name = "page") final int page
            , @RequestParam(required = false,defaultValue = "${pageSize}", name = "pageSize") final int pageSize) {

        log.debug(" GetMapping (getRestaurants) page: {} pageSize: {} ",page,pageSize);

        return new ResponseEntity<>(Pager.getPageContent(page,pageSize,service.getRestaurants())
                , HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<Restaurant>  getRestaurant( @PathVariable final  long id) {
        log.debug(" GetMapping (getRestaurant) id: {}",id);
        return new ResponseEntity<>(service.getRestaurant(id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> removeRestaurant( @PathVariable final long id) {
        log.debug(" DeleteMapping (removeRestaurant) id: {} ",id);
        return new ResponseEntity<>(service.removeRestaurant(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> addRestaurant(@RequestBody final Restaurant Restaurant) {
        log.debug(" PostMapping (addRestaurant) ");
        return new ResponseEntity<>(service.addRestaurant(Restaurant),HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable final long id,@RequestBody final Restaurant Restaurant) {
        log.debug(" PutMapping (updateRestaurant) id: {}",id);
        return new ResponseEntity<>(service.updateRestaurant(id, Restaurant),HttpStatus.CREATED);
    }
}
