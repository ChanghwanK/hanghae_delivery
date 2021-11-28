package com.hanghae.api.service;

import com.hanghae.api.dto.request.FoodRegistRequestDTO;
import com.hanghae.api.dto.request.RestaurantRegistRequestDTO;
import com.hanghae.api.dto.response.RestaurantFindResponse;
import com.hanghae.api.exception.RestaurantNotFoundException;
import com.hanghae.api.model.Food;
import com.hanghae.api.model.Restaurant;
import com.hanghae.api.repository.FoodRepository;
import com.hanghae.api.repository.RestaurantRepository;
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
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    @Transactional
    public void registRestaurant ( RestaurantRegistRequestDTO restaurantRegistRequestDTO ) {

        Restaurant restaurant = new Restaurant(
            restaurantRegistRequestDTO.getName(),
            restaurantRegistRequestDTO.getMinOrderPrice(),
            restaurantRegistRequestDTO.getDeliveryFee()
        );

        restaurant.checkValidationOrderPrice();
        restaurant.checkValidationDefaultDeliveryFee();

        restaurantRepository.save(restaurant);
    }

    @Transactional
    public void registFood ( Long restaurantId, FoodRegistRequestDTO foodRegistRequestDTO ) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
            .orElseThrow(RestaurantNotFoundException::new);

        String restaurantName= restaurant.getName();

        Food food = new Food(
            foodRegistRequestDTO.getName(),
            foodRegistRequestDTO.getPrice(),
            restaurantName
        );

        foodRepository.save(food);
    }

    @Transactional(readOnly = true)
    public List<RestaurantFindResponse> findAllRestaurants () {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return RestaurantFindResponse.listOf(restaurants);
    }
}
