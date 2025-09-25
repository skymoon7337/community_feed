package org.fastcampus.post.domain.comment;

import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommentTest {

    private final UserInfo info = new UserInfo("name", "url");
    private final User user = new User(1L, info);
    private final User otherUser = new User(2L, info);

    private final Post post = new Post(1L, user, new PostContent("content"));
    private final Comment comment = new Comment(1L, post, user, new CommentContent("content"));

    @Test
    void givenComment_whenLike_thenIncreaseLikeCount() {
        //when
        comment.like(otherUser);
        //then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenComment_whenUnLike_thenDecreaseLikeCount() {
        //given
        comment.like(otherUser);
        //when
        comment.unLike();
        //then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenComment_whenLikeBySelf_thenThrowException() {
        //when, then
        assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }

    @Test
    void givenCommentAndLike_whenUnLike_thenLikeCountIsZero() {
        //given
        comment.like(otherUser);
        //when
        comment.unLike();
        //then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenComment_whenUpdateContent_thenContentShouldBeUpdated() {
        //given
        String newCommentContent = "new content";
        //when
        comment.updateComment(user, newCommentContent);
        //then
        assertEquals(newCommentContent, comment.getContent());
    }

    @Test
    void givenComment_whenUpdateContentByOtherUser_thenThrowException() {
        //given
        String newCommentContent = "new content";
        //when, then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(otherUser, newCommentContent));
    }

    @Test
    void givenComment_whenUpdateContentOver_thenThrowException() {
        //given
        String newCommentContent = "a".repeat(101);
        //when, then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(user, newCommentContent));
    }
}
