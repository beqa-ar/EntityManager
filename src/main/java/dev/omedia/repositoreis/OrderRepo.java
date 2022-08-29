package dev.omedia.repositoreis;

import dev.omedia.domains.Order;
import dev.omedia.domains.query.OrderQueryModel;
import dev.omedia.exceptions.OrderNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public class OrderRepo {

    @PersistenceContext
    private EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public List<Order> findAll() {
        return getOrderBy(OrderQueryModel.builder().build());
    }

    public Optional<Order> findById(Long id) {
        return getOrderBy(OrderQueryModel.builder()
                .id(id)
                .build())
                .stream()
                .findFirst();
    }

    public Order update(Order order) {
        if (findById(order.getId()).isPresent()) {
            return em.merge(order);
        } else {
            throw new OrderNotFoundException(order.getId());
        }
    }

    public void remove(final long id) {
        Order order = getOrderBy(OrderQueryModel.builder().id(id).build()).stream()
                .findFirst().orElseThrow(() -> new OrderNotFoundException(id));
        em.remove(order);
    }

    private List<Order> getOrderBy(OrderQueryModel model) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> query = cb.createQuery(Order.class);
        query.where(createPredicates(cb, query.from(Order.class), model));

        return em.createQuery(query).getResultList();
    }

    private Predicate[] createPredicates(CriteriaBuilder cb, Root<Order> from, OrderQueryModel model) {
        final List<Predicate> predicates = new ArrayList<>();
        Optional.ofNullable(model.getId()).ifPresent(id -> predicates.add(cb.equal(from.get("id"), id)));

        return predicates.toArray(new Predicate[0]);
    }
}
