package com.hanghae.api.dto.request;

import com.hanghae.api.model.OrderLine;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */

@Getter
@NoArgsConstructor
public class OrderRequestDto {

    private Long restaurantId;
    private List<OrderLineInfo> foods;

    @Getter
    public static class OrderLineInfo {

        private final Long foodId;

        private final Integer quantity;

        public OrderLineInfo (Long foodId, Integer quantity) {
            this.foodId = foodId; this.quantity = quantity;
        }

        public OrderLine toEntity () {
            return OrderLine.builder().foodId(foodId).quantity(quantity).build();
        }
    }
}
