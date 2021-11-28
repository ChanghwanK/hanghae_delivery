package com.hanghae.api.dto.response;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderResponseDTO {

    private Long orderId;

    public OrderResponseDTO ( Long orderId ) {
        this.orderId = orderId;
    }
}
