package com.hanghae.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table (name = "order_line")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long foodId;

    private Integer quantity;

    public OrderLine ( Long foodId, Integer quantity ) {
        this.foodId = foodId;
        this.quantity = quantity;
    }
}
