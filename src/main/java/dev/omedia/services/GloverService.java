package dev.omedia.services;

import dev.omedia.domains.Glover;
import dev.omedia.exceptions.GloverNotFoundException;
import dev.omedia.repositoreis.GloverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GloverService {

    private final GloverRepo repo;

    @Autowired
    public GloverService(GloverRepo repo) {
        this.repo = repo;
    }

    public List<Glover> getGlovers() {
        return repo.findAll();
    }

    public Glover getGlover(final long id) {
        return repo.findById(id).orElseThrow(()-> new GloverNotFoundException(id));
    }

    public boolean addGlover(final Glover glover) {
        repo.save(glover);
        return true;
    }

    public Glover updateGlover(final long id, final Glover glover) {
        glover.setId(id);
        return repo.update(glover);
    }

    public boolean removeGlover(final long id) {
        repo.remove(id);
        return true;
    }
}
