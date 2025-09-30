package org.skymoon7337.user.application.interfaces;

import org.skymoon7337.user.domain.User;

public interface UserRepository {

    User save(User user);

    User findById(Long id);

}
