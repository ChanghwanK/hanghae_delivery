package com.hanghae.api.dto.request;

import com.hanghae.api.model.OrderLine;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */

@Getter
@NoArgsConstructor
public class OrderRequestDto {

    private Long restaurantId;
    private List<OrderLineInfo> orderLineInfos;

    @Getter
    public static class OrderLineInfo {

        private final Long foodId;

        @Range(min = 1, max = 100, message = "주문 수량을 확인 해주세요")
        private final Integer quantity;

        public OrderLineInfo (Long foodId, Integer quantity) {
            this.foodId = foodId; this.quantity = quantity;
        }

        public OrderLine toEntity () {
            return OrderLine.builder().foodId(foodId).quantity(quantity).build();
        }
    }
}
