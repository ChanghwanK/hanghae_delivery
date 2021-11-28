package com.hanghae.api.service;

import com.hanghae.api.dto.request.OrderRequestDTO;
import com.hanghae.api.dto.request.OrderRequestDTO.OrderFood;
import com.hanghae.api.dto.response.OrderFindResponseDTO;
import com.hanghae.api.dto.response.OrderFindResponseDTO.OrderFoodListResponse;
import com.hanghae.api.dto.response.OrderResponseDTO;
import com.hanghae.api.exception.FoodNotFoundException;
import com.hanghae.api.exception.OrderNotFoundException;
import com.hanghae.api.exception.RestaurantNotFoundException;
import com.hanghae.api.exception.TotalOrderPriceNotValidateException;
import com.hanghae.api.model.Food;
import com.hanghae.api.model.Order;
import com.hanghae.api.model.OrderLine;
import com.hanghae.api.model.Restaurant;
import com.hanghae.api.repository.FoodRepository;
import com.hanghae.api.repository.OrderRepository;
import com.hanghae.api.repository.RestaurantRepository;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;


    @Transactional
    public OrderResponseDTO registNewOrder ( OrderRequestDTO orderRequestDTO ) {

        Long restaurantId = orderRequestDTO.getRestaurantId();

        restaurantRepository.findById(restaurantId)
            .orElseThrow(ReadOnlyFileSystemException::new);

        List<OrderFood> orderFoods = orderRequestDTO.getOrderFoods();

        ArrayList<OrderLine> orderLines = new ArrayList<>();
        Integer totalPrice = 0;

        for ( OrderFood orderFood : orderFoods ) {

            OrderLine orderLine = new OrderLine(
                orderFood.getFoodId(),
                orderFood.getQuantity()
            );

            orderLines.add(orderLine);

            Food food = foodRepository.findById(orderFood.getFoodId())
                .orElseThrow(FoodNotFoundException::new);

            totalPrice += orderFood.calculatePrice(food);
        }

        if ( totalPrice > 0 ) {
            Order order = new Order(restaurantId, totalPrice, orderLines);
            Long orderId = orderRepository.save(order).getId();
            return new OrderResponseDTO(orderId);
        } else {
            throw new TotalOrderPriceNotValidateException();
        }
    }

    @Transactional (readOnly = true)
    public OrderFindResponseDTO findOrderById ( Long orderId ) {

        Order order = orderRepository.findById(orderId)
            .orElseThrow(OrderNotFoundException::new);

        Long restaurantId = order.getRestaurantId();

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
            .orElseThrow(RestaurantNotFoundException::new);

        Integer deliveryFee = restaurant.getDeliveryFee();
        Integer totalPrice = order.getTotalPrice();
        List<OrderLine> orderLines = order.getOrderLines();
        List<OrderFoodListResponse> orderFoodListResponses = new ArrayList<>();

        for ( OrderLine orderLine : orderLines ) {

            Long fooId = orderLine.getFoodId();
            Integer quantity = orderLine.getQuantity();

            Food food = foodRepository.findById(fooId)
                .orElseThrow(FoodNotFoundException::new);

            OrderFoodListResponse orderFindResponse = new OrderFoodListResponse(
                fooId,
                quantity,
                food.getPrice()
            );

            orderFoodListResponses.add(orderFindResponse);
        }

        return new OrderFindResponseDTO(
            orderId,
            restaurantId,
            orderFoodListResponses,
            deliveryFee,
            totalPrice
        );
    }
}
