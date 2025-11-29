package org.skymoon7337.post.domain.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("날짜 시간 정보 테스트")
class DatetimeInfoTest {

    @Test
    @DisplayName("생성된 시간을 업데이트하면 시간과 상태가 업데이트된다")
    void givenCreated_whenUpdated_thenTimeAndStateArsUpdated() throws InterruptedException {
        //given
        DatetimeInfo datetimeInfo = new DatetimeInfo();
        LocalDateTime localDateTime = datetimeInfo.getDateTime();
        Thread.sleep(10);

        //when
        datetimeInfo.updateEditDatetime();

        //then
        assertTrue(datetimeInfo.isEdited());
        assertNotEquals(localDateTime, datetimeInfo.getDateTime());
    }


}
