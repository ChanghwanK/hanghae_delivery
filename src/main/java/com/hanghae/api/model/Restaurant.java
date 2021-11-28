package com.hanghae.api.model;

import com.hanghae.api.exception.DeliveryFeeNotValidateException;
import com.hanghae.api.exception.OrderPriceNotValidateException;
import javax.persistence.Column;
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
@Table (name = "restaurant")
public class Restaurant extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer minOrderPrice;

    @Column(nullable = false)
    private Integer deliveryFee;

    public Restaurant ( String name, Integer minOrderPrice, Integer deliveryFee ) {
        this.name = name;
        this.minOrderPrice = minOrderPrice;
        this.deliveryFee = deliveryFee;
    }

    public void checkValidationOrderPrice () {
        Integer minOrderPrice = this.minOrderPrice;

        if (minOrderPrice <= 1000 || minOrderPrice >= 100000) {
            throw new OrderPriceNotValidateException();
        }
    }

    public void checkValidationDefaultDeliveryFee () {
        Integer deliveryFee = this.deliveryFee;

        if (deliveryFee <= 0 || deliveryFee >= 10000) {
            throw new DeliveryFeeNotValidateException();
        }
    }
}
