package org.skymoon7337.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserInfoTest {

    // given
    // when
    // then

    @Test
    @DisplayName("이름과 프로필 이미지가 주어지면 유저 정보가 생성된다")
    void givenNameAndProfileImage_whenCreated_thenThrowNothing() {

        // given
        String name = "abcd";
        String profileImageUrl = "";

        // when
        // then
        assertDoesNotThrow(() -> new UserInfo(name, profileImageUrl));
    }

    @Test
    @DisplayName("이름이 비어있으면 유저 정보 생성 시 예외가 발생한다")
    void givenBlankNameAndProfileImage_whenCreated_thenThrowError() {

        // given
        String name = "";
        String profileImageUrl = "";

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> new UserInfo(name, profileImageUrl));
    }
}
