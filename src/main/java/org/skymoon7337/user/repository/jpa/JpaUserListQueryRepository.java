package org.skymoon7337.user.repository.jpa;

import org.skymoon7337.user.application.dto.GetUserListResponseDto;
import org.skymoon7337.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//조회만 하는 쿼리 레포지토리로 사용
public interface JpaUserListQueryRepository extends JpaRepository<UserEntity, Long> {

    // userId의 팔로잉 리스트 -> (userId가 팔로우하는 사람들의 이름과 프사)
    //1: UserRelationEntity(ur)로부터
    //2: ur과 u(UserEntity)를 Join해서, ur.followerUserId = u.id 기준으로
    //3: ur.followingUserId = userId인 관계들만 필터링
    //4: DTO 생성자를 호출하여 객체 생성 GetUserListResponseDto(u.name, u.profileImage) -> u에 정보들이 있고, ur엔 관계만 있기 때문
    //-> 팔로잉 하는 userId와, 얘한테 팔로잉 당하는 중인 여러 Id들 -> userId의 팔로잉 목록을 Dto(이름, 프사) 로 반환
    @Query(value = "SELECT new org.skymoon7337.user.application.dto.GetUserListResponseDto(u.name, u.profileImage) "
            + "FROM UserRelationEntity ur "
            + "INNER JOIN UserEntity u ON ur.followerUserId = u.id "
            + "WHERE ur.followingUserId = :userId")
    List<GetUserListResponseDto> getFollowingUserList(Long userId);

    // userId의 팔로워 리스트 -> (userId를 팔로우하는 사람들의 이름과 프사)
    //1: UserRelationEntity(ur)로부터
    //2: ur과 u를 Join해서, ur.followingUserId = u.id 기준으로
    //3: ur.followerUserId = :userId인 관계들만 필터링
    //4: DTO 생성자를 호출하여 객체 생성 GetUserListResponseDto(u.name, u.profileImage) -> u에 정보들이 있고, ur엔 관계만 있기 때문
    //-> 팔로잉 당하는 userId와, 얘를 팔로우 하는 여러 Id들 -> userId의 팔로워 목록을 Dto(이름, 프사) 로 반환
    @Query(value = "SELECT new org.skymoon7337.user.application.dto.GetUserListResponseDto(u.name, u.profileImage) "
            + "FROM UserRelationEntity ur "
            + "INNER JOIN UserEntity u ON ur.followingUserId = u.id "
            + "WHERE ur.followerUserId = :userId")
    List<GetUserListResponseDto> getFollowerUserList(Long userId);
}
