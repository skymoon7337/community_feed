package org.skymoon7337.post.application.interfaces;

import org.skymoon7337.post.domain.Post;

public interface PostRepository {

    Post save(Post post);

    Post findById(Long id);
}
