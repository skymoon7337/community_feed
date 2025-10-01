package org.skymoon7337.post.application;

import org.skymoon7337.post.application.dto.CreatePostRequestDto;
import org.skymoon7337.post.application.dto.LikeRequestDto;
import org.skymoon7337.post.application.dto.UpdatePostRequestDto;
import org.skymoon7337.post.application.interfaces.LikeRepository;
import org.skymoon7337.post.application.interfaces.PostRepository;
import org.skymoon7337.post.domain.Post;
import org.skymoon7337.user.application.UserService;
import org.skymoon7337.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public PostService(UserService userService, PostRepository postRepository, LikeRepository likeRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id);
    }

    public Post createPost(CreatePostRequestDto dto) {
        User author = userService.getUser(dto.userId());
        Post post = Post.createPost(null, author, dto.content(), dto.state()); //빌더나 더 작은객체로 쪼개보기
        return postRepository.save(post);
    }

    //영속성 컨텍스트 없어서
    //2번 조회(SELECT) -> PostRepositoryImpl의 save에서 문제
    public Post updatePost(Long postId, UpdatePostRequestDto dto) {
        Post post = getPost(postId);
        User user = userService.getUser(dto.userId());
        post.updatePost(user, dto.content(), dto.state());
        return postRepository.save(post);
    }

    //RepositoryImpl에 하는거 대신, Post에 @Entity, 여기에 @Transactional로 구현시
    // 장점: 간단함, 매번 쿼리문 작성 안해도 됨.
    // 단점: 기능이나 DB 변경시 Service,Domain등의 코드 변경해야됨.(ex: 동시성 문제)
    public void likePost(LikeRequestDto dto) {
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(post, user)) {
            return;
        }

        post.like(user);
        likeRepository.like(post, user);
    }

    public void unlikePost(LikeRequestDto dto) {
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(post, user)) {
            post.unlike();
            likeRepository.unlike(post, user);
        }
    }
}
