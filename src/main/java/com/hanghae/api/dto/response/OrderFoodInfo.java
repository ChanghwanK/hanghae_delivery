package com.hanghae.api.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Created by Bloo
 * @Date: 2021/12/03
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderFoodInfo {

    private String name;
    private int quantity;
    private int price;

    @Builder
    public OrderFoodInfo (String name, int quantity, int price) {
        this.name = name; this.quantity = quantity; this.price = price;
    }
}
