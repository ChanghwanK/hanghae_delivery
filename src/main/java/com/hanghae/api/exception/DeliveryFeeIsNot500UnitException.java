package com.hanghae.api.exception;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */


public class DeliveryFeeIsNot500UnitException extends RuntimeException {
    private final static String MESSAGE = "배달 금액이 유효하지 않습니다.";

    public DeliveryFeeIsNot500UnitException () {
        super(MESSAGE);
    }
}
