package org.fastcampus.common.domain;

// 글이나 덧글이나 좋아요 수 증감 로직, 팔로우 팔로잉 숫자 증감이 똑같기 때문에 공통으로 뺌
public class PositiveIntegerCounter {

    private int count;

    public PositiveIntegerCounter() {
        this.count = 0;
    }

    public void increase() {
        this.count++;
    }

    public void decrease() {
        if (count <= 0) {
            return;
        }
        this.count--;
    }


}
