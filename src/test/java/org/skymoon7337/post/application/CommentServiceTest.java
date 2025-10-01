package org.skymoon7337.post.application;

import org.junit.jupiter.api.Test;
import org.skymoon7337.post.application.dto.LikeRequestDto;
import org.skymoon7337.post.application.dto.UpdateCommentRequestDto;
import org.skymoon7337.post.domain.comment.Comment;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentServiceTest extends PostApplicationTestTemplate {

    @Test
    void givenCreateCommentRequestDto_whenCreateComment_thenReturnComment() {
        //when
        Comment comment = commentService.createComment(commentRequestDto);

        //then
        String content = comment.getContent();
        assertEquals(commentContentText, content);
    }

    @Test
    void givenCreatedComment_whenUpdateComment_thenReturnUpdatedComment() {
        //given
        Comment comment = commentService.createComment(commentRequestDto);

        //when
        String updatedContent = "updated comment";
        UpdateCommentRequestDto updateRequestDto = new UpdateCommentRequestDto(user.getId(), updatedContent);
        Comment updatedComment = commentService.updateComment(comment.getId(), updateRequestDto);

        //then
        assertEquals(comment.getId(), updatedComment.getId());
        assertEquals(comment.getAuthor(), updatedComment.getAuthor());
        assertEquals(updatedContent, updatedComment.getContent());
    }

    @Test
    void givenCreatedComment_whenLikeComment_thenReturnCommentWithLike() {
        //given
        Comment comment = commentService.createComment(commentRequestDto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);

        //then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCreatedComment_whenUnlikeComment_thenReturnCommentWithNoLike() {
        //given
        Comment comment = commentService.createComment(commentRequestDto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);
        commentService.unlikeComment(likeRequestDto);

        //then
        assertEquals(0, comment.getLikeCount());
    }
}
