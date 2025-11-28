package org.skymoon7337.post.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skymoon7337.post.application.dto.LikeRequestDto;
import org.skymoon7337.post.application.dto.UpdateCommentRequestDto;
import org.skymoon7337.post.domain.comment.Comment;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentServiceTest extends PostApplicationTestTemplate {

    @Test
    @DisplayName("댓글 생성 요청이 주어지면 댓글을 생성하고 반환한다")
    void givenCreateCommentRequestDto_whenCreateComment_thenReturnComment() {
        //when
        Comment comment = commentService.createComment(commentRequestDto);

        //then
        String content = comment.getContent();
        assertEquals(commentContentText, content);
    }

    @Test
    @DisplayName("생성된 댓글을 수정하면 수정된 댓글을 반환한다")
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
    @DisplayName("생성된 댓글에 좋아요를 누르면 좋아요가 추가된 댓글을 반환한다")
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
    @DisplayName("좋아요가 눌린 댓글에 좋아요를 취소하면 좋아요가 없는 댓글을 반환한다")
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
