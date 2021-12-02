package com.hanghae.api.dto.request;

import com.hanghae.api.exception.DeliveryFeeIsNot500UnitException;
import com.hanghae.api.exception.MinOrderPriceIsNot100UnitException;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RestaurantRegistRequestDto {

    @NotBlank
    private String name;

    @Range(min = 1000, max = 100000, message = "최소 주문 금액은 1000원 ~ 100000원 입니다.")
    private Integer minOrderPrice;

    @Range(min = 0, max = 10000, message = "배달비는 0원 ~ 10000원 입니다.")
    private Integer deliveryFee;

    public RestaurantRegistRequestDto (String name, Integer minOrderPrice, Integer deliveryFee) {
        this.name = name; this.minOrderPrice = minOrderPrice; this.deliveryFee = deliveryFee;
    }

    public void checkMinOrderIs100unit () {
        int minOrderPrice = this.minOrderPrice;

        if( (minOrderPrice % 100) != 0 ) {
            throw new MinOrderPriceIsNot100UnitException();
        }
    }

    public void checkDeliverFeeIs500unit () {
        int deliveryFee = this.deliveryFee;

        if( (deliveryFee % 500) != 0 ) {
            throw new DeliveryFeeIsNot500UnitException();
        }
    }
}
