package org.skymoon7337.user.application.interfaces;

import org.skymoon7337.user.domain.User;

/**
 * UserId대신에 User객체 전체를 넘긴 이유
 * User객체의 속성을 쉽게 사용하기 위해, 트랜잭션 처리할때 편함
 * 유저 내부의 변경이 일어나도 인터페이스에는 변화가 없다
 */
public interface UserRelationRepository {

    boolean isAlreadyFollow(User user, User targetUser);

    void save(User user, User targetUser);

    void delete(User user, User targetUser);
}
