package com.hanghae.api.controller;

import com.hanghae.api.dto.request.RestaurantRegistRequestDto;
import com.hanghae.api.dto.response.RestaurantFindResponse;
import com.hanghae.api.model.Restaurant;
import com.hanghae.api.service.RestaurantService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */


@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/restaurant/register")
    public ResponseEntity<Restaurant> registRestaurant (
        @Valid @RequestBody RestaurantRegistRequestDto restaurantRegistRequestDTO) {

        Restaurant restaurant = restaurantService.registRestaurant(restaurantRegistRequestDTO);
        return ResponseEntity.ok(restaurant);
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantFindResponse>> findAllRestaurants () {
        return ResponseEntity.ok().body(restaurantService.findAllRestaurants());
    }
}
