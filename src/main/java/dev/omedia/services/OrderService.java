package dev.omedia.services;


import dev.omedia.domains.Order;
import dev.omedia.exceptions.OrderNotFoundException;
import dev.omedia.repositoreis.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepo repo;

    @Autowired
    public OrderService(OrderRepo repo) {
        this.repo = repo;
    }


    public List<Order> getOrders() {
        return repo.findAll();    }

    public Order getOrder(final long id) {
        return repo.findById(id).orElseThrow(()-> new OrderNotFoundException(id));

    }

    public boolean addOrder(final Order order) {
        repo.save(order);
        return true;
    }

    public Order updateOrder(final long id, final Order order) {
        order.setId(id);
        return repo.update(order);
    }

    public boolean removeOrder(final long id) {
        repo.remove(id);
        return true;
    }
}
