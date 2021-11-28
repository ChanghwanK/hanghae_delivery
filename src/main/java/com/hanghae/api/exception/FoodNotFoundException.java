package com.hanghae.api.exception;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */


public class FoodNotFoundException extends RuntimeException {
    private static final String MESSAGE = "해당 Id의 음식을 찾지 못했습니다.";

    public FoodNotFoundException () {
        super(MESSAGE);
    }
}
