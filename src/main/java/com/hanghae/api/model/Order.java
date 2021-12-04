package com.hanghae.api.model;

import com.hanghae.api.exception.OrderLineNotValidException;
import com.hanghae.api.exception.TotalOrderPriceNotValidateException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "ORDER_TBL")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private int totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private final List<OrderLine> orderLines = new ArrayList<>();

    public static Order getDefaultOrderInstance () {
        return new Order();
    }

    public void setRelationWithRestaurant (Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setRelationWithOrderLines (List<OrderLine> orderLines) {

        if(! orderLines.isEmpty()) {
            for(OrderLine orderLine : orderLines) {
                orderLine.registOrder(this);
            }
            this.orderLines.addAll(orderLines);
        } else {
            throw new OrderLineNotValidException();
        }
    }

    public void setTotalPriceAndCheckTotalPriceIsValid (int totalPrice, int minOrderPrice) {
        if(totalPrice <= minOrderPrice) {
            throw new TotalOrderPriceNotValidateException();
        } else {
            this.totalPrice = totalPrice;
        }
    }

}
