package com.hanghae.api.exception;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */

public class OrderPriceNotValidateException extends RuntimeException {
    private final static String MESSAGE = "주문 금액을 확인 해주세요";

    public OrderPriceNotValidateException () {
        super(MESSAGE);
    }
}
