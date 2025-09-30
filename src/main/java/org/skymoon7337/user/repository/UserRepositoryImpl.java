package org.skymoon7337.user.repository;

import lombok.RequiredArgsConstructor;
import org.skymoon7337.user.application.interfaces.UserRepository;
import org.skymoon7337.user.domain.User;
import org.skymoon7337.user.repository.entity.UserEntity;
import org.skymoon7337.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity(user);
        entity = jpaUserRepository.save(entity);
        return entity.toUser();
    }

    @Override
    public User findById(Long id) {
        UserEntity entity = jpaUserRepository
                .findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return entity.toUser();
    }
}
