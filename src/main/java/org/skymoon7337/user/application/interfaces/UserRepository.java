package org.skymoon7337.user.application.interfaces;

import org.skymoon7337.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findId(Long id);

}
