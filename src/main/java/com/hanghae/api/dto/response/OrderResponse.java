package com.hanghae.api.dto.response;

import com.hanghae.api.exception.OrderInfoIsEmptyException;
import com.hanghae.api.model.Restaurant;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderResponse {

    private String restaurantName;
    private List<OrderFoodInfo> orderFoodInfos;
    private int deliveryFee;
    private int totalPrice;

    private OrderResponse (Restaurant restaurant, int totalPrice) {
        this.restaurantName = restaurant.getName();
        this.deliveryFee = restaurant.getDeliveryFee();
        this.totalPrice = totalPrice;
    }

    public void setOrderFoodInfos (List<OrderFoodInfo> orderFoodInfos) {
        if(! orderFoodInfos.isEmpty()) {
            this.orderFoodInfos = orderFoodInfos;
        } else {
            throw new OrderInfoIsEmptyException();
        }
    }

    public static OrderResponse of (Restaurant restaurant,  int totalPrice, List<OrderFoodInfo> orderFoodInfos) {
        OrderResponse orderResponse = new OrderResponse(restaurant, totalPrice);
        orderResponse.setOrderFoodInfos(orderFoodInfos);
        return orderResponse;
    }
}
