package org.skymoon7337.post.repository;

import lombok.RequiredArgsConstructor;
import org.skymoon7337.post.application.interfaces.PostRepository;
import org.skymoon7337.post.domain.Post;
import org.skymoon7337.post.repository.entity.post.PostEntity;
import org.skymoon7337.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;

    // new PostEntity(post)로 만들어서 postId(pk)가 존재, JPA가 관리하는 객체 X (새로 만든 객체)
    // 이 PostEntity 객체를 조회한적이 없어서 DB에 SELECT(확인) 필요함 -> merge() 실행됨 (SELECT + UPDATE) // (ID가 없으면 → persist()-> INSERT, ID가 있으면 → merge() -> SELECT + UPDATE 실행)
    // 총 SELECT 3번, UPDATE 1번 = 총 4번의 쿼리 발생 // (PostService에서 updatePost에서 2번 SELECT)
    // *문제 -> 같은 Post 재조회 (getPost, merge) -> JpaPostRepository에 쿼리 작성으로 해결 (여러 방법 존재)
    @Override
    @Transactional
    public Post save(Post post) {
        PostEntity postEntity = new PostEntity(post);
        if (post.getId() != null) {
            jpaPostRepository.updatePostEntity(postEntity);
            return postEntity.toPost();
        }
        return jpaPostRepository.save(postEntity).toPost();
    }

    @Override
    public Post findById(Long id) {
        PostEntity postEntity = jpaPostRepository.findById(id).orElseThrow();
        return postEntity.toPost();
    }
}
