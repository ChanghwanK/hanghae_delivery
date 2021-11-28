package com.hanghae.api.dto.request;

import com.hanghae.api.model.Food;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderRequestDTO {

    private Long restaurantId;
    private List<OrderFood> orderFoods;

    @Getter
    public static class OrderFood {
        private Long foodId;
        private Integer quantity;

        public Integer calculatePrice( Food food ) {
            Integer price = food.getPrice() * this.getQuantity();
            return price;
        }
    }
}
