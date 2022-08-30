package dev.omedia.services;

import dev.omedia.domains.TransportType;
import dev.omedia.exceptions.TransportTypeNotFoundException;
import dev.omedia.repositoreis.TransportTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransportTypeLibraryService {
    private final TransportTypeRepo repo;

    @Autowired
    public TransportTypeLibraryService(TransportTypeRepo repo) {
        this.repo = repo;
    }

    public List<TransportType> getTransportTypes() {
        return repo.findAll();
    }

    public TransportType getTransportType(final long id) {
        return repo.findById(id).orElseThrow(()-> new TransportTypeNotFoundException(id));
    }

    public boolean addTransportType(final TransportType user) {
        repo.save(user);
        return true;
    }

    public TransportType updateTransportType(final long id, final TransportType user) {
        user.setId(id);
        return repo.update(user);
    }

    public boolean removeTransportType(final long id) {
        repo.remove(id);
        return true;
    }
}
