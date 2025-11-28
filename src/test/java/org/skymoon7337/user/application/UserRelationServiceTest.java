package org.skymoon7337.user.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skymoon7337.fake.FakeObjectFactory;
import org.skymoon7337.user.application.dto.CreateUserRequestDto;
import org.skymoon7337.user.application.dto.FollowUserRequestDto;
import org.skymoon7337.user.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserRelationServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();
    private final UserRelationService userRelationService = FakeObjectFactory.getUserRelationService();

    private User user1;
    private User user2;
    // user1,2는 항상 팔로우 팔로잉 관계가 동일해야 되기 때문에 따로 선언했다
    private FollowUserRequestDto requestDto;

    @BeforeEach
    void init() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
        this.user1 = userService.createUser(dto);
        this.user2 = userService.createUser(dto);
        // 항상 user1이 user2를 팔로우하는 관계로 가정
        this.requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    @DisplayName("두 명의 유저가 생성되고 팔로우를 하면 팔로우/팔로워 수가 증가한다")
    void givenCreateTwoUser_whenFollow_thenUserFollowSaved() {
        //when
        userRelationService.follow(requestDto);

        //then
        assertEquals(1, user1.followingCount());
        assertEquals(1, user2.followerCount());
    }

    @Test
    @DisplayName("이미 팔로우한 유저를 다시 팔로우하면 예외가 발생한다")
    void givenCreateTwoUserFollowed_whenFollow_thenUserThrowError() {
        //given
        userRelationService.follow(requestDto);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));
    }

    @Test
    @DisplayName("자기 자신을 팔로우하면 예외가 발생한다")
    void givenCreateOneUser_whenFollowSelf_thenUserThrowError() {
        //given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        //when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(sameUser));
    }

    @Test
    @DisplayName("팔로우한 유저를 언팔로우하면 팔로우/팔로워 수가 감소한다")
    void givenCreateTwoUserFollow_whenUnfollow_thenUserUnfollowSaved() {
        //given
        userRelationService.follow(requestDto);
        //when
        userRelationService.unfollow(requestDto);
        //then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user2.followerCount());
    }

    @Test
    @DisplayName("팔로우하지 않은 유저를 언팔로우하면 예외가 발생한다")
    void givenCreateTwoUser_whenUnfollow_thenUserThrowError() {
        //when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(requestDto));
    }

    @Test
    @DisplayName("자기 자신을 언팔로우하면 예외가 발생한다")
    void givenCreateOneUser_whenUnfollowSelf_thenUserThrowError() {
        //given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        //when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(sameUser));
    }
}
