package org.skymoon7337.user.repository.jpa;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.skymoon7337.user.application.dto.GetUserListResponseDto;
import org.skymoon7337.user.repository.entity.QUserEntity;
import org.skymoon7337.user.repository.entity.QUserRelationEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaUserListPagingQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private static final QUserEntity user = QUserEntity.userEntity;
    private static final QUserRelationEntity relation = QUserRelationEntity.userRelationEntity;

    // userId의 팔로워 리스트 (페이징) -> (userId를 팔로우하는 사람들의 이름과 프사, 20개씩)
    //1: Projections.fields(GetUserListResponseDto.class)로 DTO를 받겠다 (필드명이 일치하면 자동으로 매핑)
    //2: UserRelationEntity(relation)로부터
    //3: relation과 user를 Join한다. relation.followingUserId = user.id 기준으로
    //4: relation.followerUserId = userId인 관계들만 필터링 (userId를 팔로우하는 관계들)
    //5: lastFollowerId가 있으면 user.id < lastFollowerId 조건 추가 (커서 기반 페이징)
    //6: user.id 기준 내림차순 정렬 (최신 순), 최대 20개만 가져오기
    //-> 결과: userId를 팔로우 중인 사람들의 정보 리스트 (20개씩, 커서 기반 페이징)
    public List<GetUserListResponseDto> getFollwerList(Long userId, Long lastFollowerId) {
        return jpaQueryFactory
                .select(
                        Projections.fields(
                                GetUserListResponseDto.class
                        )
                )
                .from(relation)
                .join(user).on(relation.followingUserId.eq(user.id))
                //각 조건을 순회하면서 검사, null이 아닌 것만 AND로 연결, null인 것은 스킵
                .where(
                        relation.followerUserId.eq(userId),
                        hasLastData(lastFollowerId)
                )
                .orderBy(user.id.desc())
                .limit(20)
                .fetch();
    }

    // "user.id < lastId" 같은 조건을 담은 상자
    private BooleanExpression hasLastData(Long lastId) {
        if (lastId == null) {
            return null;
        }

        return user.id.lt(lastId);
    }
}
