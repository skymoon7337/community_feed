package org.skymoon7337.user.repository.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable //다른 엔티티에 포함될 수 있는 클래스, 독립적인 테이블이 아님. 다른 엔티티의 일부분으로 사용됨. 복합키를 표현하거나, 반복되는 필드를 그룹화할 때 사용
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
//상태 2가지 : 팔로잉을 하는 유저, 팔로잉을 당하는 유저
// -> 팔로잉을 당하는 Userid 를 검색후 목록조회 = 이 유저를 팔로우 하는 userId 리스트
public class UserRelationIdEntity {
    //팔로잉을 하는 유저의 아이디
    private Long followingUserId;
    //팔로잉을 당하는 유저의 아이디
    private Long followerUserId;
}
