package dev.omedia.repositoreis;

import dev.omedia.domains.RestaurantBranch;
import dev.omedia.domains.query.RestaurantBranchQueryModel;
import dev.omedia.exceptions.RestaurantBranchNotFoundException;
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
public class RestaurantBranchRepo {

    @PersistenceContext
    private EntityManager em;

    public void save(RestaurantBranch restaurantBranch) {
        em.persist(restaurantBranch);
    }

    public List<RestaurantBranch> findAll() {
        return getRestaurantBranchBy(RestaurantBranchQueryModel.builder().build());
    }
    public List<RestaurantBranch> findByRestaurant(Long restaurantId) {
        return getRestaurantBranchBy(RestaurantBranchQueryModel.builder().restaurantId(restaurantId).build());
    }

    public Optional<RestaurantBranch> findById(Long id,Long restaurantId) {
        return getRestaurantBranchBy(RestaurantBranchQueryModel.builder()
                .id(id)
                .restaurantId(restaurantId)
                .build())
                .stream()
                .findFirst();
    }

    public RestaurantBranch update(RestaurantBranch restaurantBranch) {
        if (findById(restaurantBranch.getId(), restaurantBranch.getRestaurantId()).isPresent()) {
            return em.merge(restaurantBranch);
        } else {
            throw new RestaurantBranchNotFoundException(restaurantBranch.getId(),restaurantBranch.getRestaurantId());
        }
    }

    public void remove(final long id,final long restaurantId) {
        RestaurantBranch restaurantBranch = getRestaurantBranchBy(RestaurantBranchQueryModel.builder().id(id).restaurantId(restaurantId).build()).stream()
                .findFirst().orElseThrow(() -> new RestaurantBranchNotFoundException(id,restaurantId));
        em.remove(restaurantBranch);
    }

    private List<RestaurantBranch> getRestaurantBranchBy(RestaurantBranchQueryModel model) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<RestaurantBranch> query = cb.createQuery(RestaurantBranch.class);
        query.where(createPredicates(cb, query.from(RestaurantBranch.class), model));

        return em.createQuery(query).getResultList();
    }

    private Predicate[] createPredicates(CriteriaBuilder cb, Root<RestaurantBranch> from, RestaurantBranchQueryModel model) {
        final List<Predicate> predicates = new ArrayList<>();
        Optional.ofNullable(model.getId()).ifPresent(id -> predicates.add(cb.equal(from.get("id"), id)));
        Optional.ofNullable(model.getRestaurantId()).ifPresent(restaurantId -> predicates.add(cb.equal(from.get("restaurantId"), restaurantId)));

        return predicates.toArray(new Predicate[0]);
    }
}
