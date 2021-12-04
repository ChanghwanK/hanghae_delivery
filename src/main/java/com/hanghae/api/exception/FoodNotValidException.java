package com.hanghae.api.exception;

public class FoodNotValidException extends RuntimeException {
    private static final String MESSAG = "음식 정보를 확인 해주세요";

    public FoodNotValidException () {
        super(MESSAG);
    }
}
