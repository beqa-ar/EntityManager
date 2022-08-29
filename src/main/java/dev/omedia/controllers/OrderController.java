package dev.omedia.controllers;


import dev.omedia.domains.Order;
import dev.omedia.helpers.Pager;
import dev.omedia.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("Orders")
public class OrderController {
    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Collection<Order>> getOrders(
            @RequestParam(required = false,  name = "page") final int page
            , @RequestParam(required = false,  name = "pageSize") final int pageSize) {

        log.debug(" GetMapping (getOrders) page: {} pageSize: {} ",page,pageSize);

        return new ResponseEntity<>(Pager.getPageContent(page,pageSize,service.getOrders())
                , HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<Order>  getOrder( @PathVariable final  long id) {
        log.debug(" GetMapping (getOrder) id: {}",id);
        return new ResponseEntity<>(service.getOrder(id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> removeOrder( @PathVariable final long id) {
        log.debug(" DeleteMapping (removeOrder) id: {} ",id);
        return new ResponseEntity<>(service.removeOrder(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> addOrder(@RequestBody final Order Order) {
        log.debug(" PostMapping (addOrder) ");
        return new ResponseEntity<>(service.addOrder(Order),HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable final long id,@RequestBody final Order Order) {
        log.debug(" PutMapping (updateOrder) id: {}",id);
        return new ResponseEntity<>(service.updateOrder(id, Order),HttpStatus.CREATED);
    }
}
