package com.hanghae.api.exception;

/**
 * @Created by Bloo
 * @Date: 2021/12/04
 */


public class OrderQuantityValidException extends RuntimeException {
    private static final String MESSAGE = "주문 수량이 초과 되었습니다.";

    public OrderQuantityValidException () {
        super(MESSAGE);
    }
}
