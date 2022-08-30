package dev.omedia.repositoreis;

import dev.omedia.domains.UserStatus;
import dev.omedia.domains.query.UserStatusQueryModel;
import dev.omedia.exceptions.UserStatusNotFoundException;
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
public class UserStatusRepo {

    @PersistenceContext
    private EntityManager em;

    public void save(UserStatus status) {
        em.persist(status);
    }

    public List<UserStatus> findAll() {
        return getUserStatusBy(UserStatusQueryModel.builder().build());
    }

    public Optional<UserStatus> findById(Long id) {
        return getUserStatusBy(UserStatusQueryModel.builder()
                .id(id)
                .build())
                .stream()
                .findFirst();
    }

    public UserStatus update(UserStatus status) {
        if (findById(status.getId()).isPresent()) {
            return em.merge(status);
        } else {
            throw new UserStatusNotFoundException(status.getId());
        }
    }

    public void remove(final long id) {
        UserStatus status = getUserStatusBy(UserStatusQueryModel.builder().id(id).build()).stream()
                .findFirst().orElseThrow(() -> new UserStatusNotFoundException(id));
        em.remove(status);
    }

    private List<UserStatus> getUserStatusBy(UserStatusQueryModel model) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserStatus> query = cb.createQuery(UserStatus.class);
        query.where(createPredicates(cb, query.from(UserStatus.class), model));

        return em.createQuery(query).getResultList();
    }

    private Predicate[] createPredicates(CriteriaBuilder cb, Root<UserStatus> from, UserStatusQueryModel model) {
        final List<Predicate> predicates = new ArrayList<>();
        Optional.ofNullable(model.getId()).ifPresent(id -> predicates.add(cb.equal(from.get("id"), id)));
        Optional.ofNullable(model.getName()).ifPresent(name -> predicates.add(cb.equal(from.get("name"), name)));

        return predicates.toArray(new Predicate[0]);
    }
}
