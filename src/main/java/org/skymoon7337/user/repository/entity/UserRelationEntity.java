package org.skymoon7337.user.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.skymoon7337.common.repository.entity.TimeBaseEntity;

@Entity
@Table(name = "community_user_relation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@IdClass(UserRelationIdEntity.class) //  ID가 2개(이상)다. 그 2개를 묶어서 표현하는 클래스가 UserRelationIdEntity이다.
public class UserRelationEntity extends TimeBaseEntity {

    @Id
    private Long followingUserId; //팔로잉을 하는 유저의 id

    @Id
    private Long followerUserId; //팔로우를 당하는 유저의 id

}
