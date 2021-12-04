package com.hanghae.api.model;

import com.hanghae.api.dto.request.OrderRequestDto.OrderLineInfo;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long foodId;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @Builder
    public OrderLine ( Long foodId, int quantity ) {
        this.foodId = foodId;
        this.quantity = quantity;
    }

    public void registOrder(Order order) {
        this.order = order;
    }

    public static OrderLine of ( OrderLineInfo orderLineInfo) {
        return OrderLine.builder()
            .foodId(orderLineInfo.getId())
            .quantity(orderLineInfo.getQuantity())
            .build();
    }
}
