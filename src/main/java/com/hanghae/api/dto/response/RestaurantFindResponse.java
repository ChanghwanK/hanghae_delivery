package com.hanghae.api.dto.response;

import com.hanghae.api.model.Restaurant;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantFindResponse {

    private Long id;
    private String name;
    private Integer minOrderPrice;
    private Integer deliveryFee;

    public RestaurantFindResponse ( Long id, String name, Integer minOrderPrice,
        Integer deliveryFee ) {
        this.id = id;
        this.name = name;
        this.minOrderPrice = minOrderPrice;
        this.deliveryFee = deliveryFee;
    }

    public static List<RestaurantFindResponse> listOf (List<Restaurant> restaurants) {
        ArrayList<RestaurantFindResponse> restaurantFindResponses = new ArrayList<>();

        for ( Restaurant restaurant : restaurants) {
            RestaurantFindResponse restaurantFindResponse = new RestaurantFindResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getMinOrderPrice(),
                restaurant.getMinOrderPrice()
            );

            restaurantFindResponses.add(restaurantFindResponse);
        }

        return  restaurantFindResponses;
    }
}
