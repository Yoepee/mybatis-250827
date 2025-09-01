package com.back.global.Rq;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class Rq {
    @Getter
    private int count;

    public void increaseCount() {
        count++;
    }
}
