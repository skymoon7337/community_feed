package org.fastcampus.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostContentTest {

    @Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent() {
        //given
        String text = "this is a test";
        //when
        PostContent content = new PostContent(text);
        //then
        assertEquals("this is a test", content.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenCreated_thenThrowError() {
        //given
        String text = "a".repeat(501); //경계값 확인이 중요
        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁, 닭, 굵, 삵, 솲"})
    void givenContentLengthIsOverKorean_whenCreated_thenThrowError(String koreanWord) {
        //given
        String text = koreanWord.repeat(501);
        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @Test
    void givenContentLengthIsUnder_whenCreated_thenThrowError() {
        //given
        String text = "a".repeat(4); //경계값 확인이 중요
        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsNullOrEmpty_whenCreated_thenThrowError(String value) {
        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(value));
    }

    @Test
    void givenContentLengthIsOk_whenUpdated_thenReturnTextContent() {
        //given
        String text = "this is a test";
        PostContent content = new PostContent(text);
        //when
        content.updateContent("update content");
        //then
        assertEquals("update content", content.getContentText());
    }

    @Test
    void givenContentLengthIsOk_whenUpdated_thenReturnUpdatedTextContent() {
        //given
        String text = "this is a test";
        PostContent content = new PostContent(text);
        //when
        content.updateContent("update content");
        //then
        assertEquals("update content", content.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenUpdated_thenThrowError() {
        //given
        String text = "this is a test";
        PostContent content = new PostContent(text);
        //when, then
        String updateText = "a".repeat(501);
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(updateText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁, 닭, 굵, 삵, 솲"})
    void givenContentLengthIsOverKorean_whenUpdated_thenThrowError(String koreanWord) {
        //given
        String text = "this is a test";
        PostContent content = new PostContent(text);
        //when, then
        String updateText = koreanWord.repeat(501);
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(updateText));
    }

    @Test
    void givenContentLengthIsUnder_whenUpdated_thenThrowError() {
        //given
        String text = "this is a test";
        PostContent content = new PostContent(text);
        //when, then
        String updateText = "a".repeat(4);
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(updateText));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsNullOrEmpty_whenUpdated_thenThrowError(String value) {
        //given
        String text = "this is a test";
        PostContent content = new PostContent(text);
        //when, then
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(value));
    }
}

