package org.skymoon7337.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommentContentTest {

    @Test
    void givenContentLengthIsOk_whenCreateCommentContent_thenReturnTextContext() {
        //given
        String contentText = "This is test comment.";

        //when
        CommentContent content = new CommentContent(contentText);

        //then
        assertEquals(contentText, content.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenCreateCommentContent_thenThrowError() {
        //given
        String contentText = "a".repeat(101);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(contentText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁, 닭, 굵, 삵, 솲"})
    void givenContentLengthIsOverKorean_whenCreateCommentContent_thenThrowError(String koreanWord) {
        //given
        String contentText = koreanWord.repeat(101);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(contentText));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsNullOrEmpty_whenCreateCommentContent_thenThrowError(String value) {
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(value));
    }


}
