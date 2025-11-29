package org.skymoon7337.user.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skymoon7337.fake.FakeObjectFactory;
import org.skymoon7337.user.application.dto.CreateUserRequestDto;
import org.skymoon7337.user.domain.User;
import org.skymoon7337.user.domain.UserInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("유저 서비스 테스트")
class UserServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();

    @Test
    @DisplayName("유저 정보가 주어지면 유저를 생성하고 찾을 수 있다")
    void givenUserInfoDto_whenCreatUser_thenCanFindUser() {
        //given
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");

        //when
        User savedUser = userService.createUser(dto);

        //then
        User foundUser = userService.getUser(savedUser.getId());
        UserInfo userInfo = foundUser.getInfo();

        assertEquals(foundUser.getId(), savedUser.getId());
        assertEquals("test", userInfo.getName());
    }
}
