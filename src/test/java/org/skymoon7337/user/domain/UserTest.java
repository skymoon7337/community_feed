package org.skymoon7337.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {

    private final UserInfo userInfo = new UserInfo("test", "");
    private User user1;
    private User user2;

    @BeforeEach
    void init() {
        user1 = new User(1L, userInfo);
        user2 = new User(2L, userInfo);
    }

    @Test
    @DisplayName("두 유저의 ID가 다르면 동일하지 않다")
    void givenTwoUsers_whenEqual_thenReturnFalse() {
        //when
        boolean isSame = user1.equals(user2);

        //then
        assertFalse(isSame);
    }

    @Test
    @DisplayName("두 유저의 ID가 같으면 동일하다")
    void givenTwoSameIdUser_whenEqual_thenReturnTrue() {
        //given
        User sameUser = new User(1L, userInfo);

        //when
        boolean isSame = user1.equals(sameUser);

        //then
        assertTrue(isSame);
    }

    @Test
    @DisplayName("두 유저의 ID가 다르면 해시코드가 다르다")
    void givenTwoUser_whenHashCode_thenNotEqual() {
        // when
        int hashCode1 = user1.hashCode();
        int hashCode2 = user2.hashCode();

        // then
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("두 유저의 ID가 같으면 해시코드가 같다")
    void givenTwoSameIdUser_whenHashCode_thenEqual() {
        //given
        User sameUser = new User(1L, userInfo);

        //when
        int hashCode1 = user1.hashCode();
        int sameUserHashCode = sameUser.hashCode();

        //then
        assertEquals(hashCode1, sameUserHashCode);
    }

    @Test
    @DisplayName("유저1이 유저2를 팔로우하면 팔로우/팔로워 수가 증가한다")
    void givenTwoUser_whenUser1FollowUser2_thenIncreaseUserCount() {
        //when
        user1.follow(user2);

        //then
        assertEquals(1, user1.followingCount());
        assertEquals(0, user1.followerCount());

        assertEquals(0, user2.followingCount());
        assertEquals(1, user2.followerCount());
    }

    @Test
    @DisplayName("유저1이 유저2를 언팔로우하면 팔로우/팔로워 수가 감소한다")
    void givenTwoUserUser1FollowUser2_whenUnfollow_thenDecreaseUserCount() {
        //given
        user1.follow(user2);

        //when
        user1.unfollow(user2);

        //then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user1.followerCount());

        assertEquals(0, user2.followingCount());
        assertEquals(0, user2.followerCount());
    }

    @Test
    @DisplayName("팔로우하지 않은 유저를 언팔로우해도 팔로우/팔로워 수는 변하지 않는다")
    void givenTwoUser_whenUnfollow_thenNotDecreaseUserCount() {
        //when
        user1.unfollow(user2);

        //then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user1.followerCount());

        assertEquals(0, user2.followingCount());
        assertEquals(0, user2.followerCount());
    }
}
