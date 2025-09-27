package org.skymoon7337.post.application.dto;

public record UpdateCommentRequestDto(Long commentId, Long userId, String content) {
}
