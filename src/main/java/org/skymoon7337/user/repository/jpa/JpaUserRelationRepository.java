package org.skymoon7337.user.repository.jpa;

import org.skymoon7337.user.repository.entity.UserRelationEntity;
import org.skymoon7337.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {
}
