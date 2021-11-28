package com.hanghae.api.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderFindResponseDTO {

    private Long orderId;
    private Long restaurantId;
    private List<OrderFoodListResponse> foods;
    private Integer deliveryFee;
    private Integer totalPrice;

    public OrderFindResponseDTO ( Long orderId, Long restaurantId,
        List<OrderFoodListResponse> foods, Integer deliveryFee, Integer totalPrice ) {
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.foods = foods;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
    }

    @Getter
    public static class OrderFoodListResponse {
        private Long foodId;
        private Integer quantity;
        private Integer price;

        public OrderFoodListResponse ( Long foodId, Integer quantity, Integer price ) {
            this.foodId = foodId;
            this.quantity = quantity;
            this.price = price;
        }
    }
}