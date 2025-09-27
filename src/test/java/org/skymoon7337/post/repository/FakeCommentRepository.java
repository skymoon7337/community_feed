package org.skymoon7337.post.repository;

import org.skymoon7337.post.application.interfaces.CommentRepository;
import org.skymoon7337.post.domain.comment.Comment;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeCommentRepository implements CommentRepository {

    private final Map<Long, Comment> store = new HashMap<>();

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() != null) {
            store.put(comment.getId(), comment);
            return comment;
        }

        long id = store.size() + 1L;
        Comment newComment = new Comment(id, comment.getPost(), comment.getAuthor(), comment.getContentObject());
        store.put(id, newComment);
        return newComment;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
