package org.skymoon7337.user.ui;

import lombok.RequiredArgsConstructor;
import org.skymoon7337.common.ui.Response;
import org.skymoon7337.user.application.UserService;
import org.skymoon7337.user.application.dto.CreateUserRequestDto;
import org.skymoon7337.user.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserRequestDto dto) {
        User user = userService.createUser(dto);
        return Response.ok(user.getId());
    }
}
