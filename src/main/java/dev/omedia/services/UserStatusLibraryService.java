package dev.omedia.services;


import dev.omedia.domains.UserStatus;
import dev.omedia.exceptions.UserStatusNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserStatusLibraryService {
    private final Set<UserStatus> UserStatus = new HashSet<>();

    public List<UserStatus> getUserStatus() {
        return List.copyOf(UserStatus);
    }

    public UserStatus getUserStatus(final long id) {
        return UserStatus.stream()
                .filter(UserStatus -> UserStatus.getId() == id)
                .findAny()
                .orElseThrow(() -> new UserStatusNotFoundException(id));
    }

    public boolean addUserStatus(final UserStatus UserStatus) {
        long maxId = getUserStatus().stream()
                .map(dev.omedia.domains.UserStatus::getId)
                .max(Long::compare)
                .orElse(0L);
        UserStatus.setId(maxId + 1);
        return this.UserStatus.add(UserStatus);
    }

    public boolean updateUserStatus(final long id, final UserStatus UserStatus) {
        if (this.removeUserStatus(id)) {
            UserStatus.setId(id);
        }
        return this.addUserStatus(UserStatus);
    }

    public boolean removeUserStatus(final long id) {
        UserStatus UserStatus = this.UserStatus.stream()
                .filter(t -> t.getId() == id)
                .findAny()
                .orElseThrow(() -> new UserStatusNotFoundException(id));
        return this.UserStatus.remove(UserStatus);
    }
}
