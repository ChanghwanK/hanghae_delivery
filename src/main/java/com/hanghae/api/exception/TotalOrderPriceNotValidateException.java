package com.hanghae.api.exception;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */


public class TotalOrderPriceNotValidateException extends RuntimeException {

    private static final String MESSAGE = "유효하지 않은 총 가격 입니다.";

    public TotalOrderPriceNotValidateException () {
        super(MESSAGE);
    }
}
