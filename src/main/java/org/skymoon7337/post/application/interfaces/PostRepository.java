package org.skymoon7337.post.application.interfaces;

import org.skymoon7337.post.domain.Post;

import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    Optional<Post> findById(Long id);
}
