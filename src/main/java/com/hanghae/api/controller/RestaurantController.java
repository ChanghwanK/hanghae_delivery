package com.hanghae.api.controller;

import com.hanghae.api.dto.request.FoodRegistRequestDTO;
import com.hanghae.api.dto.request.RestaurantRegistRequestDTO;
import com.hanghae.api.dto.response.RestaurantFindResponse;
import com.hanghae.api.service.RestaurantService;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping ("/api/restaurant")
    public ResponseEntity<Void> registRestaurant ( @RequestBody RestaurantRegistRequestDTO restaurantRegistRequestDTO ) {

        restaurantService.registRestaurant(restaurantRegistRequestDTO);
        return ResponseEntity.created(URI.create("/api/restaurant")).build();
    }

    @PostMapping ("/api/restaurant/{restaurantId}/food/register")
    public ResponseEntity<Void> registRestaurantFood (
        @PathVariable (name = "restaurantId") Long id,
        @RequestBody FoodRegistRequestDTO foodRegistRequestDTO ) {

        restaurantService.registFood(id, foodRegistRequestDTO);

        return ResponseEntity.created(URI.create("/api/restaurant/{restaurantId}food/register")).build();
    }


    @GetMapping("/api/user/restaurants")
    public ResponseEntity<List<RestaurantFindResponse>> findAllRestaurants() {
        return ResponseEntity.ok().body(restaurantService.findAllRestaurants());
    }
}
