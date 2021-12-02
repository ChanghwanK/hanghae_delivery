package com.hanghae.api.exception;

/**
 * @Created by Bloo
 * @Date: 2021/12/03
 */


public class OrderInfoIsEmptyException extends RuntimeException {
    private static final String MESSAGE = "주문 정보가 비었습니다.";
    public OrderInfoIsEmptyException () {
        super(MESSAGE);
    }
}
