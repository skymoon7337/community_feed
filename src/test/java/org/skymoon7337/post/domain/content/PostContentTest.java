package org.skymoon7337.post.domain.content;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("게시글 내용 테스트")
class PostContentTest {

    @Test
    @DisplayName("게시글 내용의 길이가 유효하면 게시글 내용이 생성된다")
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent() {
        //given
        String text = "this is a test";
        //when
        PostContent content = new PostContent(text);
        //then
        assertEquals("this is a test", content.getContentText());
    }

    @Test
    @DisplayName("게시글 내용의 길이가 500자를 초과하면 생성 시 예외가 발생한다")
    void givenContentLengthIsOver_whenCreated_thenThrowError() {
        //given
        String text = "a".repeat(501); //경계값 확인이 중요
        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁, 닭, 굵, 삵, 솲"})
    @DisplayName("한글 게시글 내용의 길이가 500자를 초과하면 생성 시 예외가 발생한다")
    void givenContentLengthIsOverKorean_whenCreated_thenThrowError(String koreanWord) {
        //given
        String text = koreanWord.repeat(501);
        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @Test
    @DisplayName("게시글 내용의 길이가 5자 미만이면 생성 시 예외가 발생한다")
    void givenContentLengthIsUnder_whenCreated_thenThrowError() {
        //given
        String text = "a".repeat(4); //경계값 확인이 중요
        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("게시글 내용이 null이거나 비어있으면 생성 시 예외가 발생한다")
    void givenContentLengthIsNullOrEmpty_whenCreated_thenThrowError(String value) {
        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(value));
    }

    @Test
    @DisplayName("게시글 내용의 길이가 유효하면 게시글 내용이 수정된다")
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
    @DisplayName("게시글 내용의 길이가 유효하면 수정된 게시글 내용이 반환된다")
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
    @DisplayName("게시글 내용의 길이가 500자를 초과하면 수정 시 예외가 발생한다")
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
    @DisplayName("한글 게시글 내용의 길이가 500자를 초과하면 수정 시 예외가 발생한다")
    void givenContentLengthIsOverKorean_whenUpdated_thenThrowError(String koreanWord) {
        //given
        String text = "this is a test";
        PostContent content = new PostContent(text);
        //when, then
        String updateText = koreanWord.repeat(501);
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(updateText));
    }

    @Test
    @DisplayName("게시글 내용의 길이가 5자 미만이면 수정 시 예외가 발생한다")
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
    @DisplayName("게시글 내용이 null이거나 비어있으면 수정 시 예외가 발생한다")
    void givenContentLengthIsNullOrEmpty_whenUpdated_thenThrowError(String value) {
        //given
        String text = "this is a test";
        PostContent content = new PostContent(text);
        //when, then
        assertThrows(IllegalArgumentException.class, () -> content.updateContent(value));
    }
}

