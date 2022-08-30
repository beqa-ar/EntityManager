package dev.omedia.controllers;

import dev.omedia.domains.RestaurantBranch;
import dev.omedia.helpers.Pager;
import dev.omedia.services.RestaurantBranchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("/Restaurants/{restaurantId}/RestaurantBranches")
public class RestaurantBranchController {
    private final RestaurantBranchService service;

    @Autowired
    public RestaurantBranchController(RestaurantBranchService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Collection<RestaurantBranch>> getRestaurantBranches(
            @RequestParam(required = false,defaultValue = "${page}", name = "page") final int page
            , @RequestParam(required = false,defaultValue = "${pageSize}", name = "pageSize") final int pageSize
            , @PathVariable long restaurantId) {

        log.debug(" GetMapping (getRestaurantBranches) page: {} pageSize: {} ",page,pageSize);

        return new ResponseEntity<>(Pager.getPageContent(page,pageSize,service.getRestaurantBranches(restaurantId))
                , HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<RestaurantBranch>  getRestaurantBranch( @PathVariable final  long id
            ,@PathVariable long restaurantId) {
        log.debug(" GetMapping (getRestaurantBranch) id: {}",id);
        return new ResponseEntity<>(service.getRestaurantBranch(id,restaurantId),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> removeRestaurantBranch(@PathVariable final long id
            , @PathVariable long restaurantId) {
        log.debug(" DeleteMapping (removeRestaurantBranch) id: {} ",id);
        return new ResponseEntity<>(service.removeRestaurantBranch(id,restaurantId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> addRestaurantBranch(@RequestBody final RestaurantBranch restaurantBranch) {
        log.debug(" PostMapping (addRestaurantBranch) ");
        return new ResponseEntity<>(service.addRestaurantBranch(restaurantBranch),HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<RestaurantBranch> updateRestaurantBranch(@PathVariable final long id
            , @PathVariable long restaurantId
            ,@RequestBody final RestaurantBranch restaurantBranch) {
        log.debug(" PutMapping (updateRestaurantBranch) id: {}",id);
        return new ResponseEntity<>(service.updateRestaurantBranch(id,restaurantId,restaurantBranch),HttpStatus.CREATED);
    }
}
