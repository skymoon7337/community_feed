package org.skymoon7337.post.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skymoon7337.post.domain.content.PostContent;
import org.skymoon7337.user.domain.User;
import org.skymoon7337.user.domain.UserInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("게시글 테스트")
class PostTest {
    private final UserInfo info = new UserInfo("name", "url");
    private final User user = new User(1L, info);
    private final User otherUser = new User(2L, info);
    private final Post post = new Post(1L, user, new PostContent("content"));


    @Test
    @DisplayName("게시글에 좋아요를 누르면 좋아요 수가 증가한다")
    void givenPostCreated_whenLike_thenIncreaseLikeCount() {
        //when
        post.like(otherUser);
        //then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    @DisplayName("자신의 게시글에 좋아요를 누르면 예외가 발생한다")
    void givenPostCreated_whenLikeByOtherUser_thenThrowException() {
        //when, then
        assertThrows(IllegalArgumentException.class, () -> post.like(user));
    }

    @Test
    @DisplayName("좋아요가 눌린 게시글에 좋아요를 취소하면 좋아요 수가 감소한다")
    void givenPostLiked_whenUnlike_thenDecreaseLikeCount() {
        //given
        post.like(otherUser);
        //when
        post.unlike();
        //then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    @DisplayName("게시글에 좋아요를 누르지 않고 취소하면 좋아요 수는 0을 유지한다")
    void givenPostCreated_whenUnlike_thenLikeCountIsZero() {
        //when
        post.unlike();
        //then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    @DisplayName("게시글 내용을 수정하면 내용이 수정되어야 한다")
    void givenPostCreated_whenUpdateContent_thenContentShouldBeUpdated() {
        //given
        String newPostContent = "new content";
        //when
        post.updatePost(user, newPostContent, PostPublicationState.PUBLIC);
        //then
        assertEquals(newPostContent, post.getContent());
    }

    @Test
    @DisplayName("다른 사용자가 게시글 내용을 수정하려고 하면 예외가 발생한다")
    void givenPostCreated_whenUpdateContentByOtherUser_thenThrowException() {
        //given
        String newPostContent = "new content";
        //when, then
        assertThrows(IllegalArgumentException.class, () -> post.updatePost(otherUser, newPostContent, PostPublicationState.PUBLIC));
    }
}
