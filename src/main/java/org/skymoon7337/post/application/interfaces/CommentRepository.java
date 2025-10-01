package org.skymoon7337.post.application.interfaces;

import org.skymoon7337.post.domain.comment.Comment;

public interface CommentRepository {

    Comment save(Comment comment);

    Comment findById(Long id);
}
