package com.hanghae.api.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */


@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FoodRegistRequestDTO {

    private String name;
    private Integer price;
}
