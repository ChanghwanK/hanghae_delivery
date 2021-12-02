package com.hanghae.api.exception;

/**
 * @Created by Bloo
 * @Date: 2021/12/01
 */


public class MinOrderPriceIsNot100UnitException extends RuntimeException {
    private static final String MESSAGE = "최소주문 금액은 100원 단위여야 합니다.";

    public MinOrderPriceIsNot100UnitException () {
        super(MESSAGE);
    }
}
