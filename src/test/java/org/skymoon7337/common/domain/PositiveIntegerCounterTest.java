package org.skymoon7337.common.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("양의 정수 카운터 테스트")
class PositiveIntegerCounterTest {

    @Test
    @DisplayName("생성된 카운터를 증가시키면 카운트는 1이 된다")
    void givenCreated_whenIncrease_thenCountIsOne() {

        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        // when
        counter.increase();

        // then
        assertEquals(1, counter.getCount());
    }

    @Test
    @DisplayName("증가된 카운터를 감소시키면 카운트는 0이 된다")
    void givenCreated_whenIncreasedAndDecrease_thenCountIsZero() {

        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        counter.increase();

        // when
        counter.decrease();

        // then
        assertEquals(0, counter.getCount());
    }

    @Test
    @DisplayName("생성된 카운터를 감소시키면 카운트는 0을 유지한다")
    void givenCreated_whenDecrease_thenCountIsZero() {

        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        // when
        counter.decrease();

        // then
        assertEquals(0, counter.getCount());
    }

}
