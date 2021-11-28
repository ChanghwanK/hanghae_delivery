package com.hanghae.api.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RestaurantRegistRequestDTO {

    private static final Integer MIN_ORDER_PRICE = 1000;
    private static final Integer MAX_ORDER_PRICE = 100000;
    private static final Integer MIN_DELIVERY_PRICE = 0;
    private static final Integer MAX_DELIVERY_PRICE = 10000;

    @NotBlank
    private String name;

    @Min(MIN_ORDER_PRICE)
    @Max (MAX_ORDER_PRICE)
    private Integer minOrderPrice;

    @Min(MIN_DELIVERY_PRICE)
    @Max(MAX_DELIVERY_PRICE)
    private Integer deliveryFee;

}
