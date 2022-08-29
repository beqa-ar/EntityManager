package dev.omedia.services;

import dev.omedia.domains.TransportType;
import dev.omedia.exceptions.TransportTypeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class TransportTypeLibraryService {
    private final Set<TransportType> transportTypes = new HashSet<>();

    public List<TransportType> getTransportTypes() {
        return List.copyOf(transportTypes);
    }

    public TransportType getTransportType(final long id) {
        return transportTypes.stream()
                .filter(TransportType -> TransportType.getId() == id)
                .findAny()
                .orElseThrow(() -> new TransportTypeNotFoundException(id));
    }

    public boolean addTransportType(final TransportType TransportType) {
        long maxId = getTransportTypes().stream()
                .map(dev.omedia.domains.TransportType::getId)
                .max(Long::compare)
                .orElse(0L);
        TransportType.setId(maxId + 1);
        return this.transportTypes.add(TransportType);
    }

    public boolean updateTransportType(final long id, final TransportType TransportType) {
        if (this.removeTransportType(id)) {
            TransportType.setId(id);
        }
        return this.addTransportType(TransportType);
    }

    public boolean removeTransportType(final long id) {
        TransportType TransportType = this.transportTypes.stream()
                .filter(t -> t.getId() == id)
                .findAny()
                .orElseThrow(() -> new TransportTypeNotFoundException(id));
        return this.transportTypes.remove(TransportType);
    }
}
