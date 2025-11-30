package org.skymoon7337.fake;

import org.skymoon7337.post.application.CommentService;
import org.skymoon7337.post.application.PostService;
import org.skymoon7337.post.application.interfaces.CommentRepository;
import org.skymoon7337.post.application.interfaces.LikeRepository;
import org.skymoon7337.post.application.interfaces.PostRepository;
import org.skymoon7337.post.repository.FakeCommentRepository;
import org.skymoon7337.post.repository.FakeLikeRepository;
import org.skymoon7337.post.repository.FakePostRepository;
import org.skymoon7337.user.application.UserRelationService;
import org.skymoon7337.user.application.UserService;
import org.skymoon7337.user.application.interfaces.UserRelationRepository;
import org.skymoon7337.user.application.interfaces.UserRepository;
import org.skymoon7337.user.repository.FakeUserRelationRepository;
import org.skymoon7337.user.repository.FakeUserRepository;


//싱글톤 Mock 객체를 제공한다
public class FakeObjectFactory {

    private static final UserRepository fakeUserRepository = new FakeUserRepository();
    private static final UserRelationRepository fakeUserRelationRepository = new FakeUserRelationRepository();
    private static final PostRepository fakePostRepository = new FakePostRepository();
    private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
    private static final LikeRepository fakeLikeRepository = new FakeLikeRepository();

    private static final UserService userService = new UserService(fakeUserRepository);
    private static final UserRelationService userRelationService = new UserRelationService(userService, fakeUserRelationRepository);
    private static final PostService postService = new PostService(userService, fakePostRepository, fakeLikeRepository);
    private static final CommentService commentService = new CommentService(userService, postService, fakeCommentRepository, fakeLikeRepository);

    private FakeObjectFactory() {
    }

    public static UserService getUserService() {
        return userService;
    }

    public static UserRelationService getUserRelationService() {
        return userRelationService;
    }

    public static PostService getPostService() {
        return postService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }
}
