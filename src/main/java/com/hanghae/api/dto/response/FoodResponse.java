package com.hanghae.api.dto.response;

import com.hanghae.api.model.Food;
import lombok.Builder;

/**
 * @Created by Bloo
 * @Date: 2021/12/03
 */


public class FoodResponse {

    private Long id;
    private String name;
    private int price;

    @Builder
    public FoodResponse (Long id, String name, int price) {
        this.id = id; this.name = name; this.price = price;
    }

    public static FoodResponse of (Food food) {
        return FoodResponse.builder().id(food.getId()).name(food.getName()).price(food.getPrice())
            .build();
    }
}
