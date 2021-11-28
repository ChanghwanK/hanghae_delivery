package com.hanghae.api.exception;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */


public class OrderNotFoundException extends RuntimeException {
    private static final String MESSAGE = "해당하는 ID의 주문을 조회할 수 없습니다.";

    public OrderNotFoundException () {
        super(MESSAGE);
    }
}
