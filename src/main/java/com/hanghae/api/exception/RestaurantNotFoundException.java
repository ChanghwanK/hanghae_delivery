package com.hanghae.api.exception;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */


public class RestaurantNotFoundException extends RuntimeException {
    private static final String MESSAGE = "해당 ID의 레스토랑을 찾을 수 없습니다.";

    public RestaurantNotFoundException () {
        super(MESSAGE);
    }
}
