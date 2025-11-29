package org.skymoon7337.post.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skymoon7337.post.application.dto.LikeRequestDto;
import org.skymoon7337.post.application.dto.UpdatePostRequestDto;
import org.skymoon7337.post.domain.Post;
import org.skymoon7337.post.domain.PostPublicationState;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Post와 Comment는 로직이 비슷해서 템플릿으로 뺌
@DisplayName("게시글 서비스 테스트")
class PostServiceTest extends PostApplicationTestTemplate {

    @Test
    @DisplayName("게시글 생성 요청이 주어지면 게시글을 생성하고 반환한다")
    void givenPostRequestDto_whenCreate_thenReturnPost() {
        //when
        Post savedPost = postService.createPost(postRequestDto);

        //then
        Post post = postService.getPost(savedPost.getId());
        assertEquals(savedPost, post);
    }

    @Test
    @DisplayName("생성된 게시글을 수정하면 수정된 게시글을 반환한다")
    void givenCreatePost_whenUpdate_thenReturnUpdatedPost() {
        //given
        Post savedPost = postService.createPost(postRequestDto);

        //when
        UpdatePostRequestDto updatePostRequestDto = new UpdatePostRequestDto(user.getId(), "this is test", PostPublicationState.PUBLIC);
        Post updatedPost = postService.updatePost(savedPost.getId(), updatePostRequestDto);

        //then
        assertEquals(savedPost.getId(), updatedPost.getId());
        assertEquals(savedPost.getContent(), updatedPost.getContent());
        assertEquals(savedPost.getAuthor(), updatedPost.getAuthor());
        assertEquals(savedPost.getState(), updatedPost.getState());
    }

    @Test
    @DisplayName("생성된 게시글에 좋아요를 누르면 좋아요가 추가된 게시글을 반환한다")
    void givenCreatedPost_whenLiked_thenReturnPostWithLike() {
        //given
        Post savedPost = postService.createPost(postRequestDto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        //then
        assertEquals(1, savedPost.getLikeCount());
    }

    @Test
    @DisplayName("생성된 게시글에 좋아요를 두 번 누르면 좋아요가 하나만 추가된 게시글을 반환한다")
    void givenCreatedPost_whenLikedTwice_thenReturnPostWithOneLike() {
        //given
        Post savedPost = postService.createPost(postRequestDto);

        //when
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);
        postService.likePost(likeRequestDto);

        //then
        assertEquals(1, savedPost.getLikeCount());
    }

    @Test
    @DisplayName("좋아요가 눌린 게시글에 좋아요를 취소하면 좋아요가 없는 게시글을 반환한다")
    void givenLikedPost_whenUnliked_thenReturnPostWithNoLike() {
        //given
        Post savedPost = postService.createPost(postRequestDto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        //when
        postService.unlikePost(likeRequestDto);

        //then
        assertEquals(0, savedPost.getLikeCount());
    }

    @Test
    @DisplayName("생성된 게시글에 좋아요를 취소하면 좋아요가 없는 게시글을 유지한다")
    void givenCreatedPost_whenUnliked_thenReturnPostWithNoLike() {
        //given
        Post savedPost = postService.createPost(postRequestDto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.unlikePost(likeRequestDto);

        //when
        postService.unlikePost(likeRequestDto);

        //then
        assertEquals(0, savedPost.getLikeCount());
    }
}
