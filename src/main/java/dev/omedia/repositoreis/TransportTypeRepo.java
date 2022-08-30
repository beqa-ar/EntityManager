package dev.omedia.repositoreis;

import dev.omedia.domains.TransportType;
import dev.omedia.domains.query.TransportTypeQueryModel;
import dev.omedia.exceptions.TransportTypeNotFoundException;
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
public class TransportTypeRepo {

    @PersistenceContext
    private EntityManager em;

    public void save(TransportType type) {
        em.persist(type);
    }

    public List<TransportType> findAll() {
        return getTransportTypeBy(TransportTypeQueryModel.builder().build());
    }

    public Optional<TransportType> findById(Long id) {
        return getTransportTypeBy(TransportTypeQueryModel.builder()
                .id(id)
                .build())
                .stream()
                .findFirst();
    }

    public TransportType update(TransportType type) {
        if (findById(type.getId()).isPresent()) {
            return em.merge(type);
        } else {
            throw new TransportTypeNotFoundException(type.getId());
        }
    }

    public void remove(final long id) {
        TransportType type = getTransportTypeBy(TransportTypeQueryModel.builder().id(id).build()).stream()
                .findFirst().orElseThrow(() -> new TransportTypeNotFoundException(id));
        em.remove(type);
    }

    private List<TransportType> getTransportTypeBy(TransportTypeQueryModel model) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TransportType> query = cb.createQuery(TransportType.class);
        query.where(createPredicates(cb, query.from(TransportType.class), model));

        return em.createQuery(query).getResultList();
    }

    private Predicate[] createPredicates(CriteriaBuilder cb, Root<TransportType> from, TransportTypeQueryModel model) {
        final List<Predicate> predicates = new ArrayList<>();
        Optional.ofNullable(model.getId()).ifPresent(id -> predicates.add(cb.equal(from.get("id"), id)));
        Optional.ofNullable(model.getName()).ifPresent(name -> predicates.add(cb.equal(from.get("name"), name)));

        return predicates.toArray(new Predicate[0]);
    }
}
