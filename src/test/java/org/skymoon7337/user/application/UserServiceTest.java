package org.skymoon7337.user.application;

import org.junit.jupiter.api.Test;
import org.skymoon7337.fake.FakeObjectFactory;
import org.skymoon7337.user.application.dto.CreateUserRequestDto;
import org.skymoon7337.user.domain.User;
import org.skymoon7337.user.domain.UserInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();

    @Test
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
