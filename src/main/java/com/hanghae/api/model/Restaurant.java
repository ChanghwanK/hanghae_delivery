package com.hanghae.api.model;

import com.hanghae.api.exception.DeliveryFeeIsNot500UnitException;
import com.hanghae.api.exception.OrderPriceNotValidateException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */

@ToString
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
}
