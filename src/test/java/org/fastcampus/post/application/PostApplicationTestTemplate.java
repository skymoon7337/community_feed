package org.fastcampus.post.application;

import org.fastcampus.fake.FakeObjectFactory;
import org.fastcampus.post.application.dto.CreateCommentRequestDto;
import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.PostPublicationState;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.domain.User;

public class PostApplicationTestTemplate {


    final UserService userService = FakeObjectFactory.getUserService();
    final PostService postService = FakeObjectFactory.getPostService();
    final CommentService commentService = FakeObjectFactory.getCommentService();


    final User user = userService.createUser(new CreateUserRequestDto("user1", null));
    final User otherUser = userService.createUser(new CreateUserRequestDto("user1", null));

    final String commentContentText = "test comment";

    final CreatePostRequestDto postRequestDto = new CreatePostRequestDto(user.getId(), commentContentText, PostPublicationState.PUBLIC);
    final Post post = postService.createPost(postRequestDto);

    CreateCommentRequestDto commentRequestDto = new CreateCommentRequestDto(post.getId(), user.getId(), commentContentText);

}
