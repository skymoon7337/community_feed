package org.skymoon7337.post.repository.jpa;

import org.skymoon7337.post.repository.entity.like.LikeEntity;
import org.skymoon7337.post.repository.entity.like.LikeIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeIdEntity> {
}

