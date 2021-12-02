package com.hanghae.api.exception.handler;

import com.hanghae.api.dto.response.ErrorResponse;
import com.hanghae.api.exception.DeliveryFeeIsNot500UnitException;
import com.hanghae.api.exception.OrderPriceNotValidateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderPriceNotValidateException.class)
    public ErrorResponse handlerOrderPriceNotValidateException ( OrderPriceNotValidateException ex ) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DeliveryFeeIsNot500UnitException.class)
    public ErrorResponse handleDeliveryFeeNotValidException ( DeliveryFeeIsNot500UnitException ex ) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ResponseStatus (HttpStatus.BAD_REQUEST)
    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException (
        MethodArgumentNotValidException ex ) {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getFieldError());
    }



}
