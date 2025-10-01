package org.skymoon7337.post.repository.jpa;

import org.skymoon7337.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

    @Modifying // 데이터를 변경하는 쿼리 선언 (UPDATE, DELETE, INSERT)
    @Query(value = "UPDATE PostEntity p " //수동 쿼리 선언
            + "SET p.content =:#{#postEntity.getContent()}, " //p의 content 칼럼(게시글 내용)을 postEntity.getContent()로 바꿔
            + "p.state = :#{#postEntity.getState()}, " //p의 state를 postEntity.getState()로, p의 upDt를 지금 시간으로 바꿔
            + "p.upDt = now() "  //DB에서 id = postEntity.id인 행을 찾아서
            + "WHERE p.id = :#{#postEntity.id}")
    void updatePostEntity(PostEntity postEntity);


    @Modifying
    @Query(value = "UPDATE PostEntity p "
            + "SET p.likeCount = :#{#postEntity.likeCount}, "
            + "p.upDt = now() "
            + "WHERE p.id = :#{#postEntity.getId()}")
    void updateLikeCount(PostEntity postEntity);
}
