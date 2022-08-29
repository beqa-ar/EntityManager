package dev.omedia.repositoreis;

import dev.omedia.domains.Glover;
import dev.omedia.domains.query.GloverQueryModel;
import dev.omedia.exceptions.GloverNotFoundException;
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
public class GloverRepo {

    @PersistenceContext
    private EntityManager em;

    public void save(Glover glover) {
        em.persist(glover);
    }

    public List<Glover> findAll() {
        return getGloverBy(GloverQueryModel.builder().build());
    }

    public Optional<Glover> findById(Long id) {
        return getGloverBy(GloverQueryModel.builder()
                .id(id)
                .build())
                .stream()
                .findFirst();
    }

    public Glover update(Glover glover) {
        if (findById(glover.getId()).isPresent()) {
            return em.merge(glover);
        } else {
            throw new GloverNotFoundException(glover.getId());
        }
    }

    public void remove(final long id) {
        Glover glover = getGloverBy(GloverQueryModel.builder().id(id).build()).stream()
                .findFirst().orElseThrow(() -> new GloverNotFoundException(id));
        em.remove(glover);
    }

    private List<Glover> getGloverBy(GloverQueryModel model) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Glover> query = cb.createQuery(Glover.class);
        query.where(createPredicates(cb, query.from(Glover.class), model));

        return em.createQuery(query).getResultList();
    }

    private Predicate[] createPredicates(CriteriaBuilder cb, Root<Glover> from, GloverQueryModel model) {
        final List<Predicate> predicates = new ArrayList<>();
        Optional.ofNullable(model.getId()).ifPresent(id -> predicates.add(cb.equal(from.get("id"), id)));
        Optional.ofNullable(model.getName()).ifPresent(name -> predicates.add(cb.equal(from.get("name"), name)));

        return predicates.toArray(new Predicate[0]);
    }
}
