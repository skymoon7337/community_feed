package org.skymoon7337.user.repository;

import org.skymoon7337.user.application.interfaces.UserRepository;
import org.skymoon7337.user.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeUserRepository implements UserRepository {
    private final Map<Long, User> store = new HashMap<>();

    @Override
    public User save(User user) {
        if (user.getId() != null) {
            store.put(user.getId(), user);
        }
        Long id = store.size() + 1L;
        User newUser = new User(id, user.getInfo());
        store.put(id, newUser);
        return newUser;
    }

    @Override
    public Optional<User> findId(Long id) {
        //값이 null이면, 비어있는 Optional 객체 (Optional.empty())를 반환
        return Optional.ofNullable(store.get(id));
    }
}
