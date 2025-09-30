package org.skymoon7337.user.repository;

import lombok.RequiredArgsConstructor;
import org.skymoon7337.user.application.interfaces.UserRelationRepository;
import org.skymoon7337.user.domain.User;
import org.skymoon7337.user.repository.entity.UserEntity;
import org.skymoon7337.user.repository.entity.UserRelationEntity;
import org.skymoon7337.user.repository.entity.UserRelationIdEntity;
import org.skymoon7337.user.repository.jpa.JpaUserRelationRepository;
import org.skymoon7337.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {

    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserRepository jpaUserRepository;

    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        //JpaUserRelationRepository 덕분에 existsById에 복합키를 넘길수 있음
        return jpaUserRelationRepository.existsById(id);
    }

    @Override
    @Transactional
    public void save(User user, User targetUser) {
        UserRelationEntity entity = new UserRelationEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.save(entity);
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
    }

    @Override
    @Transactional
    public void delete(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.deleteById(id);
        //서비스 레이어에서 unfollow를 하면서 팔로잉 팔로워 카운트 감소시켜서 그냥 저장하면 됨.
        jpaUserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
    }
}
