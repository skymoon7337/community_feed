package org.skymoon7337.user.repository.jpa;

import org.skymoon7337.user.repository.entity.UserRelationEntity;
import org.skymoon7337.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//extends JpaRepository<UserRelationEntity(저장 타입), UserRelationIdEntity(ID 타입(복합키))>
public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {
}
