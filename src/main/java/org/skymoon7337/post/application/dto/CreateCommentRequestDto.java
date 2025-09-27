package org.skymoon7337.post.application.dto;

public record CreateCommentRequestDto(Long postId, Long userId, String content) {
}
