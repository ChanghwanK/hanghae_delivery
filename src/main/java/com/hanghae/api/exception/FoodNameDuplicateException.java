package com.hanghae.api.exception;

/**
 * @Created by Bloo
 * @Date: 2021/12/04
 */


public class FoodNameDuplicateException extends RuntimeException {
    private static final String MESSAGE = "음식명 중복";

    public FoodNameDuplicateException () {
        super(MESSAGE);
    }
}
