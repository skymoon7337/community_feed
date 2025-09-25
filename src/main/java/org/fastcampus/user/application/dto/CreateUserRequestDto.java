package org.fastcampus.user.application.dto;

//final 객체로만 이루어져있으면 생성자, getter, EqualsAndHashCode등 자동생성해주는 record로 변경 가능
public record CreateUserRequestDto(String name, String profileImageUrl) {
}
