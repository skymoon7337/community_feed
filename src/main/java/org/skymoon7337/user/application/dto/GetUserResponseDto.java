package org.skymoon7337.user.application.dto;

import org.skymoon7337.user.domain.User;

//record는 this로만 생성자 선언 가능
public record GetUserResponseDto(Long id, String name, String profileImage, Integer followingCount,
                                 Integer followerCount) {

    public GetUserResponseDto(User user) {
        this(user.getId(), user.getName(), user.getProfileImage(), user.followingCount(), user.followerCount());
    }

}
