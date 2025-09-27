package org.skymoon7337.post.application.dto;

import org.skymoon7337.post.domain.PostPublicationState;

public record UpdatePostRequestDto(Long postId, Long userId, String content, PostPublicationState state) {
}
