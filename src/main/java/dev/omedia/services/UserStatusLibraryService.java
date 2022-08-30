package dev.omedia.services;


import dev.omedia.domains.UserStatus;
import dev.omedia.exceptions.UserStatusNotFoundException;
import dev.omedia.repositoreis.UserStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStatusLibraryService {
    private final UserStatusRepo repo;

    @Autowired
    public UserStatusLibraryService(UserStatusRepo repo) {
        this.repo = repo;
    }

    public List<UserStatus> getUserStatuses() {
        return repo.findAll();
    }

    public UserStatus getUserStatus(final long id) {
        return repo.findById(id).orElseThrow(()-> new UserStatusNotFoundException(id));
    }

    public boolean addUserStatus(final UserStatus status) {
        repo.save(status);
        return true;
    }

    public UserStatus updateUserStatus(final long id, final UserStatus status) {
        status.setId(id);
        return repo.update(status);
    }

    public boolean removeUserStatus(final long id) {
        repo.remove(id);
        return true;
    }
}
