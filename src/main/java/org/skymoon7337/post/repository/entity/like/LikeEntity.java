package org.skymoon7337.post.repository.entity.like;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.skymoon7337.common.repository.entity.TimeBaseEntity;
import org.skymoon7337.post.domain.Post;
import org.skymoon7337.post.domain.comment.Comment;
import org.skymoon7337.user.domain.User;

@Entity
@Table(name = "community_like")
@NoArgsConstructor
@Getter
public class LikeEntity extends TimeBaseEntity {

    @EmbeddedId
    private LikeIdEntity id;

    public LikeEntity(Post post, User likeedUser) {
        this.id = new LikeIdEntity(post.getId(), likeedUser.getId(), LikeTarget.POST.name());
    }

    public LikeEntity(Comment comment, User likeedUser) {
        this.id = new LikeIdEntity(comment.getId(), likeedUser.getId(), LikeTarget.COMMENT.name());
    }
}
