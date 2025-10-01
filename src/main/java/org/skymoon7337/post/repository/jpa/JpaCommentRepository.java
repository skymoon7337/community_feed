package org.skymoon7337.post.repository.jpa;

import org.skymoon7337.post.repository.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query(value = "UPDATE CommentEntity c "
            + "SET c.content =:#{#commentEntity.getContent()}, "
            + "c.upDt = now()"
            + "WHERE c.id = :#{#commentEntity.getId()}")
    void updateCommentEntity(CommentEntity commentEntity);

    @Modifying
    @Query(value = "UPDATE CommentEntity c "
            + "SET c.likeCount = :#{#commentEntity.likeCount}, "
            + "c.upDt = now() "
            + "WHERE c.id = :#{#commentEntity.getId()}")
    void updateLikeCount(CommentEntity commentEntity);
}

