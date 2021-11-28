package com.hanghae.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hanghae.api.dto.request.FoodRegistRequestDTO;
import com.hanghae.api.dto.request.RestaurantRegistRequestDTO;
import com.hanghae.api.dto.response.RestaurantFindResponse;
import com.hanghae.api.model.Food;
import com.hanghae.api.model.Restaurant;
import com.hanghae.api.repository.FoodRepository;
import com.hanghae.api.repository.RestaurantRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.persistence.Table;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */

@ExtendWith (MockitoExtension.class)
class RestaurantServiceTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private FoodRepository foodRepository;

    @DisplayName("레스토랑 등록 테스트")
    @Test
    void registRestaurant() {
        // given
        String restaurantName = "쉐이크쉑 청담점";
        Integer minOrderPrice = 5000;
        Integer deliveryFee = 2000;


        RestaurantRegistRequestDTO restaurantRegistRequestDTO = new RestaurantRegistRequestDTO(
            restaurantName,
            minOrderPrice,
            deliveryFee
        );

        restaurantService.registRestaurant(restaurantRegistRequestDTO);

        // then
        verify(restaurantRepository, times(1)).save(any());
    }

    @DisplayName ("음식등록을 테스트")
    @Test
    void registNewFood () {
        // given
        String foodName = "쉑버거 더블";
        Integer price = 10900;

        FoodRegistRequestDTO foodRegistRequestDTO = new FoodRegistRequestDTO(
            foodName,
            price
        );

        Restaurant restaurant = new Restaurant(
            "쉐이크쉑 청담점",
            5000,
            2000
        );

        // when
        when(restaurantRepository.findById(anyLong()))
            .thenReturn(Optional.of(restaurant));

        restaurantService.registFood( anyLong(), foodRegistRequestDTO);

        // then
        verify(foodRepository, times(1)).save(any());
    }

    @DisplayName ("레스토랑 조회를 테스트")
    @Test
    void findAllRestaurants () {
        // given
        Restaurant restaurant = new Restaurant(
            "쉐이크쉑 청담점",
            5000,
            2000
        );

        Restaurant restaurant_02 = new Restaurant(
            "쉐이크쉑 분당점",
            4000,
            3000
        );

        // when
        when(restaurantRepository.findAll())
            .thenReturn(Arrays.asList(restaurant, restaurant_02));

        List<RestaurantFindResponse> restaurants = restaurantService.findAllRestaurants();

        // then
        assertAll(
            () -> assertThat(restaurants.size()).isEqualTo(2),
            () -> assertThat(restaurants.get(0).getName()).isEqualTo("쉐이크쉑 청담점"),
            () -> assertThat(restaurants.get(1).getName()).isEqualTo("쉐이크쉑 분당점")
        );
    }
}