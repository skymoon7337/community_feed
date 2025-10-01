package org.skymoon7337.post.application;

import org.junit.jupiter.api.Test;
import org.skymoon7337.post.application.dto.LikeRequestDto;
import org.skymoon7337.post.application.dto.UpdatePostRequestDto;
import org.skymoon7337.post.domain.Post;
import org.skymoon7337.post.domain.PostPublicationState;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Post와 Comment는 로직이 비슷해서 템플릿으로 뺌
class PostServiceTest extends PostApplicationTestTemplate {

    @Test
    void givenPostRequestDto_whenCreate_thenReturnPost() {
        //when
        Post savedPost = postService.createPost(postRequestDto);

        //then
        Post post = postService.getPost(savedPost.getId());
        assertEquals(savedPost, post);
    }

    @Test
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
