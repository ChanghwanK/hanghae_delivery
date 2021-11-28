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

    @NotBlank
    private String name;

    @Min(1000)
    @Max (100000)
    private Integer minOrderPrice;

    @Min(0)
    @Max(10000)
    private Integer deliveryFee;

    public RestaurantRegistRequestDTO ( String name, Integer minOrderPrice,
        Integer deliveryFee ) {
        this.name = name;
        this.minOrderPrice = minOrderPrice;
        this.deliveryFee = deliveryFee;
    }
}
