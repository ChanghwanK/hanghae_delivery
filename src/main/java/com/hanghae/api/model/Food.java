package com.hanghae.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table (name = "food")
public class Food extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    public Food ( String name, int price ) {
        this.name = name;
        this.price = price;
    }

    @Builder
    public Food ( String name, int price, Restaurant restaurant ) {
        this.name = name;
        this.price = price;
    }

    public void registRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
