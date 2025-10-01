package org.skymoon7337.post.application.interfaces;

import org.skymoon7337.post.domain.Post;
import org.skymoon7337.post.domain.comment.Comment;
import org.skymoon7337.user.domain.User;

public interface LikeRepository {
    boolean checkLike(Post post, User user);

    void like(Post post, User user);

    void unlike(Post post, User user);

    boolean checkLike(Comment comment, User user);

    void like(Comment comment, User user);

    void unlike(Comment comment, User user);


}
