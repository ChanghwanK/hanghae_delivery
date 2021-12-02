package com.hanghae.api.exception;

/**
 * @Created by Bloo
 * @Date: 2021/12/03
 */


public class OrderLineNotValidException extends RuntimeException {
    private static final String MESSAGE = "주문 정보를 확인 해주세요";

    public OrderLineNotValidException () {
        super(MESSAGE);
    }
}
