package com.hanghae.api.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "ORDER_TBL")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @JoinColumn(name = "restaurant_id")
    private Long restaurantId;

    private Integer totalPrice;

    @OneToMany(fetch = FetchType.LAZY)
    private List<OrderLine> orderLines = new ArrayList<>();

    public Order (Long restaurantId, Integer totalPrice ,List<OrderLine> orderLines ) {
        this.restaurantId = restaurantId;
        this.totalPrice = totalPrice;
        this.orderLines = orderLines;
    }
}
