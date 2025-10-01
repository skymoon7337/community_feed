package org.skymoon7337.post.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.skymoon7337.post.application.interfaces.LikeRepository;
import org.skymoon7337.post.domain.Post;
import org.skymoon7337.post.domain.comment.Comment;
import org.skymoon7337.post.repository.entity.comment.CommentEntity;
import org.skymoon7337.post.repository.entity.like.LikeEntity;
import org.skymoon7337.post.repository.entity.post.PostEntity;
import org.skymoon7337.post.repository.jpa.JpaCommentRepository;
import org.skymoon7337.post.repository.jpa.JpaLikeRepository;
import org.skymoon7337.post.repository.jpa.JpaPostRepository;
import org.skymoon7337.user.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

    //save시에 merge 발생해서 불필요한 select 한번 더 발생
    // -> merge 대신 ersist로 해결 (insert문만 발생)
    // persist()는 같은 id면 에러생김 -> 지금 프로젝트는 체크해서 pk유일성 보장됨(likePost등에서 if checkLike로 이미 좋아요시에 그냥 리턴)
    //같은 트랜잭션 안에선 JpaRepository와 EntityManager는 같은 영속성 컨텍스트 공유 (JPA도 EntityManager로 저장하는 구조임)

    //EntityManager는 @Entity들을 관리함
    //LikeEntity의 @Entity와 @Table(name = "community_like")를 통해, DB 테이블(community_like)과 매핑됨을 알음
    //-> entityManager.persist(likeEntity)시 위의 정보로 community_like에 매핑해줌
    @PersistenceContext
    private final EntityManager entityManager;

    private final JpaPostRepository jpaPostRepository;
    private final JpaCommentRepository jpaCommentRepository;
    private final JpaLikeRepository jpaLikeRepository;

    @Override
    public boolean checkLike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    @Transactional
    public void like(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        //jpaLikeRepository.save(likeEntity);
        entityManager.persist(likeEntity);
        jpaPostRepository.updateLikeCount(new PostEntity(post));

    }

    @Override
    @Transactional
    public void unlike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaPostRepository.updateLikeCount(new PostEntity(post));
    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    @Transactional
    public void like(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        //jpaLikeRepository.save(likeEntity);
        entityManager.persist(likeEntity);
        jpaCommentRepository.updateLikeCount(new CommentEntity(comment));
    }

    @Override
    @Transactional
    public void unlike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaCommentRepository.updateLikeCount(new CommentEntity(comment));
    }
}
