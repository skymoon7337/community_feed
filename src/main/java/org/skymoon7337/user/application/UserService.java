package org.skymoon7337.user.application;

import org.skymoon7337.user.application.dto.CreateUserRequestDto;
import org.skymoon7337.user.application.dto.GetUserResponseDto;
import org.skymoon7337.user.application.interfaces.UserRepository;
import org.skymoon7337.user.domain.User;
import org.skymoon7337.user.domain.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto dto) {
        UserInfo info = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null, info);
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    //GetUserResponseDto 즉 유저의 전체 정보가 반횐됨
    public GetUserResponseDto getUserProfile(Long id) {
        User user = getUser(id);
        return new GetUserResponseDto(user);
    }


}
