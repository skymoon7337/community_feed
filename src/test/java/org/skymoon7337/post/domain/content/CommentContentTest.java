package org.skymoon7337.post.domain.content;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("댓글 내용 테스트")
public class CommentContentTest {

    @Test
    @DisplayName("댓글 내용의 길이가 유효하면 댓글 내용이 생성된다")
    void givenContentLengthIsOk_whenCreateCommentContent_thenReturnTextContext() {
        //given
        String contentText = "This is test comment.";

        //when
        CommentContent content = new CommentContent(contentText);

        //then
        assertEquals(contentText, content.getContentText());
    }

    @Test
    @DisplayName("댓글 내용의 길이가 100자를 초과하면 예외가 발생한다")
    void givenContentLengthIsOver_whenCreateCommentContent_thenThrowError() {
        //given
        String contentText = "a".repeat(101);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(contentText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁, 닭, 굵, 삵, 솲"})
    @DisplayName("한글 댓글 내용의 길이가 100자를 초과하면 예외가 발생한다")
    void givenContentLengthIsOverKorean_whenCreateCommentContent_thenThrowError(String koreanWord) {
        //given
        String contentText = koreanWord.repeat(101);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(contentText));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("댓글 내용이 null이거나 비어있으면 예외가 발생한다")
    void givenContentLengthIsNullOrEmpty_whenCreateCommentContent_thenThrowError(String value) {
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(value));
    }


}
