package com.hanghae.api.dto.request;

import com.hanghae.api.model.Food;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */


@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FoodRegistRequestDto {

    private String name;

    @Range(min = 100, max = 1000000, message = "최소 주문 금액은 1000원 ~ 1,000,000원 입니다.")
    private Integer price;

    public FoodRegistRequestDto (String name, Integer price) {
        this.name = name; this.price = price;
    }

    public Food toEntity () {
        return Food.builder().name(name).price(price).build();
    }
}
