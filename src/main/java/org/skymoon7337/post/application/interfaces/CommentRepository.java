package org.skymoon7337.post.application.interfaces;

import org.skymoon7337.post.domain.comment.Comment;

import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);

    Optional<Comment> findById(Long id);
}
