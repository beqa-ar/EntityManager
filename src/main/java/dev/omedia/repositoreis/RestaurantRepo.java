package dev.omedia.repositoreis;

import dev.omedia.domains.Restaurant;
import dev.omedia.domains.query.RestaurantQueryModel;
import dev.omedia.exceptions.RestaurantNotFoundException;
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
public class RestaurantRepo {

    @PersistenceContext
    private EntityManager em;

    public void save(Restaurant restaurant) {
        em.persist(restaurant);
    }

    public List<Restaurant> findAll() {
        return getRestaurantBy(RestaurantQueryModel.builder().build());
    }

    public Optional<Restaurant> findById(Long id) {
        return getRestaurantBy(RestaurantQueryModel.builder()
                .id(id)
                .build())
                .stream()
                .findFirst();
    }

    public Restaurant update(Restaurant restaurant) {
        if (findById(restaurant.getId()).isPresent()) {
            return em.merge(restaurant);
        } else {
            throw new RestaurantNotFoundException(restaurant.getId());
        }
    }

    public void remove(final long id) {
        Restaurant restaurant = getRestaurantBy(RestaurantQueryModel.builder().id(id).build()).stream()
                .findFirst().orElseThrow(() -> new RestaurantNotFoundException(id));
        em.remove(restaurant);
    }

    private List<Restaurant> getRestaurantBy(RestaurantQueryModel model) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Restaurant> query = cb.createQuery(Restaurant.class);
        query.where(createPredicates(cb, query.from(Restaurant.class), model));

        return em.createQuery(query).getResultList();
    }

    private Predicate[] createPredicates(CriteriaBuilder cb, Root<Restaurant> from, RestaurantQueryModel model) {
        final List<Predicate> predicates = new ArrayList<>();
        Optional.ofNullable(model.getId()).ifPresent(id -> predicates.add(cb.equal(from.get("id"), id)));
        Optional.ofNullable(model.getName()).ifPresent(name -> predicates.add(cb.equal(from.get("name"), name)));

        return predicates.toArray(new Predicate[0]);
    }
}
