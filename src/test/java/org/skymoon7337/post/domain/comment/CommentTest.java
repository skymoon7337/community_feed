package org.skymoon7337.post.domain.comment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skymoon7337.post.domain.Post;
import org.skymoon7337.post.domain.content.CommentContent;
import org.skymoon7337.post.domain.content.PostContent;
import org.skymoon7337.user.domain.User;
import org.skymoon7337.user.domain.UserInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("댓글 테스트")
class CommentTest {

    private final UserInfo info = new UserInfo("name", "url");
    private final User user = new User(1L, info);
    private final User otherUser = new User(2L, info);

    private final Post post = new Post(1L, user, new PostContent("content"));
    private final Comment comment = new Comment(1L, post, user, new CommentContent("content"));

    @Test
    @DisplayName("댓글에 좋아요를 누르면 좋아요 수가 증가한다")
    void givenComment_whenLike_thenIncreaseLikeCount() {
        //when
        comment.like(otherUser);
        //then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    @DisplayName("댓글에 좋아요를 취소하면 좋아요 수가 감소한다")
    void givenComment_whenUnLike_thenDecreaseLikeCount() {
        //given
        comment.like(otherUser);
        //when
        comment.unLike();
        //then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    @DisplayName("자신의 댓글에 좋아요를 누르면 예외가 발생한다")
    void givenComment_whenLikeBySelf_thenThrowException() {
        //when, then
        assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }

    @Test
    @DisplayName("좋아요가 눌린 댓글에 좋아요를 취소하면 좋아요 수는 0이 된다")
    void givenCommentAndLike_whenUnLike_thenLikeCountIsZero() {
        //given
        comment.like(otherUser);
        //when
        comment.unLike();
        //then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    @DisplayName("댓글 내용을 수정하면 내용이 수정되어야 한다")
    void givenComment_whenUpdateContent_thenContentShouldBeUpdated() {
        //given
        String newCommentContent = "new content";
        //when
        comment.updateComment(user, newCommentContent);
        //then
        assertEquals(newCommentContent, comment.getContent());
    }

    @Test
    @DisplayName("다른 사용자가 댓글 내용을 수정하려고 하면 예외가 발생한다")
    void givenComment_whenUpdateContentByOtherUser_thenThrowException() {
        //given
        String newCommentContent = "new content";
        //when, then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(otherUser, newCommentContent));
    }

    @Test
    @DisplayName("댓글 내용이 글자수 제한을 초과하면 예외가 발생한다")
    void givenComment_whenUpdateContentOver_thenThrowException() {
        //given
        String newCommentContent = "a".repeat(101);
        //when, then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(user, newCommentContent));
    }
}
