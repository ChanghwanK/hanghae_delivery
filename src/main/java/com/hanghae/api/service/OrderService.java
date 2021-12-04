package com.hanghae.api.service;

import com.hanghae.api.dto.request.OrderRequestDto;
import com.hanghae.api.dto.response.OrderFoodInfo;
import com.hanghae.api.dto.response.OrderResponse;
import com.hanghae.api.exception.FoodNotFoundException;
import com.hanghae.api.exception.OrderQuantityValidException;
import com.hanghae.api.exception.RestaurantNotFoundException;
import com.hanghae.api.model.Food;
import com.hanghae.api.model.Order;
import com.hanghae.api.model.OrderLine;
import com.hanghae.api.model.Restaurant;
import com.hanghae.api.repository.FoodRepository;
import com.hanghae.api.repository.OrderLineRepository;
import com.hanghae.api.repository.OrderRepository;
import com.hanghae.api.repository.RestaurantRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderLineRepository orderLineRepository;


    @Transactional
    public OrderResponse registNewOrder (OrderRequestDto orderRequestDto) {

        Long restaurantId = orderRequestDto.getRestaurantId();
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
            .orElseThrow(RestaurantNotFoundException::new);

        List<OrderLine> orderLines = getOrderLines(orderRequestDto);
        int totalPrice = getTotalPrice(orderLines);

        checkOrderRequestQuantity(orderLines);
        checkOrderMinOrderPrice(totalPrice, restaurant.getMinOrderPrice());
        Order order = createOrderAndSetRelation(totalPrice, restaurant, orderLines);
        orderRepository.save(order);

        return OrderResponse.of(restaurant, totalPrice, createOrderInfos(orderLines));
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> findAllOrder () {

        ArrayList<OrderResponse> orderResponses = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();

        for(Order order : orders) {
            Restaurant restaurant = order.getRestaurant();
            int totalPrice = order.getTotalPrice();

            List<OrderLine> orderLines = orderLineRepository.findAllByOrder(order);
            List<OrderFoodInfo> orderInfos = createOrderInfos(orderLines);

            orderResponses.add(OrderResponse.of(restaurant, totalPrice, orderInfos));
        }

        return orderResponses;
    }

    private int getTotalPrice (List<OrderLine> orderLines) {
        int totalPrice = 0;

        for(OrderLine orderLine : orderLines) {
            Long foodId = orderLine.getFoodId(); Food food = findFoodById(foodId);
            int orderLineUnitPrice = (food.getPrice() * orderLine.getQuantity());
            totalPrice += orderLineUnitPrice;
        }
        return totalPrice;
    }

    private List<OrderLine> getOrderLines (OrderRequestDto orderRequestDto) {
        return orderRequestDto.getFoods()
            .stream()
            .map(OrderLine::of)
            .collect(Collectors.toList());
    }


    private Order createOrderAndSetRelation (int totalPrice, Restaurant restaurant, List<OrderLine> orderLines) {
        Order order = Order.getDefaultOrderInstance();
        order.setTotalPriceAndCheckTotalPriceIsValid(totalPrice, restaurant.getMinOrderPrice());
        order.setRelationWithOrderLines(orderLines);
        order.setRelationWithRestaurant(restaurant);
        return order;
    }


    private List<OrderFoodInfo> createOrderInfos (List<OrderLine> orderLines) {

        ArrayList<OrderFoodInfo> orderFoodInfos = new ArrayList<>();

        for(OrderLine orderLine : orderLines) {
            Long foodId = orderLine.getFoodId(); Food food = findFoodById(foodId);

            OrderFoodInfo foodInfo = OrderFoodInfo.builder()
                .name(food.getName())
                .quantity(orderLine.getQuantity())
                .price(food.getPrice() * orderLine.getQuantity())
                .build();

            orderFoodInfos.add(foodInfo);
        }

        return orderFoodInfos;
    }

    private Food findFoodById (Long foodId) {
        return foodRepository.findById(foodId).orElseThrow(FoodNotFoundException::new);
    }

    private void checkOrderRequestQuantity(List<OrderLine> orderLines) {
        final int MAX_QUANTITY = 100;
        final int MIN_QUANTITY = 0;
        for (OrderLine orderLine : orderLines) {
            int orderQuantity = orderLine.getQuantity();
            if ( MIN_QUANTITY > orderQuantity || orderQuantity > MAX_QUANTITY) {
                throw new OrderQuantityValidException();
            }
        }
    }

    private void checkOrderMinOrderPrice(int totalOrderPrice, int restaurantOrderMinPrice) {
        if (totalOrderPrice < restaurantOrderMinPrice) {
            throw new OrderQuantityValidException();
        }
    }
}
