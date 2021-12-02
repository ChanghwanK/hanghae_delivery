package com.hanghae.api.controller;

import com.hanghae.api.dto.request.FoodRegistRequestDto;
import com.hanghae.api.dto.response.FoodResponse;
import com.hanghae.api.service.FoodService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created by Bloo
 * @Date: 2021/12/02
 */

@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodService foodService;

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public ResponseEntity<Void> registRestaurantFood (@PathVariable Long restaurantId,
        @Valid @RequestBody List<FoodRegistRequestDto> foodRegistRequestDtos) {

        foodService.saveNewFoods(restaurantId, foodRegistRequestDtos);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public ResponseEntity<FoodResponse> findMenusByRestaurantId (@PathVariable Long restaurantId) {

        return null;
    }

}
