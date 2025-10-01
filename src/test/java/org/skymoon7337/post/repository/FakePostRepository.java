package org.skymoon7337.post.repository;

import org.skymoon7337.post.application.interfaces.PostRepository;
import org.skymoon7337.post.domain.Post;

import java.util.HashMap;
import java.util.Map;

public class FakePostRepository implements PostRepository {
    private final Map<Long, Post> store = new HashMap<>();

    @Override
    public Post save(Post post) {
        if (post.getId() != null) {
            store.put(post.getId(), post);
            return post;
        }
        long id = store.size() + 1L;
        Post newPost = new Post(id, post.getAuthor(), post.getContentObject());
        store.put(id, newPost);
        return newPost;
    }

    @Override
    public Post findById(Long id) {
        return store.get(id);
    }
}
