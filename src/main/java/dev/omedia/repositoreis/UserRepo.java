package dev.omedia.repositoreis;

import dev.omedia.domains.User;
import dev.omedia.domains.query.UserQueryModel;
import dev.omedia.exceptions.UserNotFoundException;
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
public class UserRepo {

    @PersistenceContext
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public List<User> findAll() {
        return getUserBy(UserQueryModel.builder().build());
    }

    public Optional<User> findById(Long id) {
        return getUserBy(UserQueryModel.builder()
                .id(id)
                .build())
                .stream()
                .findFirst();
    }

    public User update(User user) {
        if (findById(user.getId()).isPresent()) {
            return em.merge(user);
        } else {
            throw new UserNotFoundException(user.getId());
        }
    }

    public void remove(final long id) {
        User user = getUserBy(UserQueryModel.builder().id(id).build()).stream()
                .findFirst().orElseThrow(() -> new UserNotFoundException(id));
        em.remove(user);
    }

    private List<User> getUserBy(UserQueryModel model) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        query.where(createPredicates(cb, query.from(User.class), model));

        return em.createQuery(query).getResultList();
    }

    private Predicate[] createPredicates(CriteriaBuilder cb, Root<User> from, UserQueryModel model) {
        final List<Predicate> predicates = new ArrayList<>();
        Optional.ofNullable(model.getId()).ifPresent(id -> predicates.add(cb.equal(from.get("id"), id)));
        Optional.ofNullable(model.getNickName()).ifPresent(name -> predicates.add(cb.equal(from.get("name"), name)));

        return predicates.toArray(new Predicate[0]);
    }
}
