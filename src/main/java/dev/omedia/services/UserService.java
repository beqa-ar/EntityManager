package dev.omedia.services;

import dev.omedia.domains.User;
import dev.omedia.exceptions.UserNotFoundException;
import dev.omedia.repositoreis.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private final UserRepo repo;

    @Autowired
    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public List<User> getUsers() {
        return repo.findAll();
    }

    public User getUser(final long id) {
        return repo.findById(id).orElseThrow(()-> new UserNotFoundException(id));
    }

    public boolean addUser(final User user) {
        repo.save(user);
        return true;
    }

    public User updateUser(final long id, final User user) {
        user.setId(id);
        return repo.update(user);
    }

    public boolean removeUser(final long id) {
        repo.remove(id);
        return true;
    }
}
