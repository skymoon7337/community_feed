package org.skymoon7337.post.repository;

import lombok.RequiredArgsConstructor;
import org.skymoon7337.post.application.interfaces.CommentRepository;
import org.skymoon7337.post.domain.comment.Comment;
import org.skymoon7337.post.repository.entity.comment.CommentEntity;
import org.skymoon7337.post.repository.jpa.JpaCommentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;


    @Override
    @Transactional //@Modifying 쿼리 이용시 필수
    public Comment save(Comment comment) {
        CommentEntity commentEntity = new CommentEntity(comment);
        if (comment.getId() != null) {
            jpaCommentRepository.updateCommentEntity(commentEntity);
            return comment;
        }
        return jpaCommentRepository.save(commentEntity).toComment();
    }

    @Override
    public Comment findById(Long id) {
        CommentEntity commentEntity = jpaCommentRepository.findById(id).orElseThrow();
        return commentEntity.toComment();
    }
}
