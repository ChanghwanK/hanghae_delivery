package com.hanghae.api.service;

import com.hanghae.api.dto.request.FoodRegistRequestDto;
import com.hanghae.api.dto.response.FoodResponse;
import com.hanghae.api.exception.RestaurantNotFoundException;
import com.hanghae.api.model.Food;
import com.hanghae.api.model.Restaurant;
import com.hanghae.api.repository.FoodRepository;
import com.hanghae.api.repository.RestaurantRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;


    @Transactional
    public void saveNewFoods (Long restaurantId, List<FoodRegistRequestDto> foodSaveRequestDtos) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
            .orElseThrow(RestaurantNotFoundException::new);

        for (FoodRegistRequestDto foodRegistRequestDto : foodSaveRequestDtos) {
            Food food = foodRegistRequestDto.toEntity();
            food.registRestaurant(restaurant);
            foodRepository.save(food);
        }
    }


    @Transactional(readOnly = true)
    public List<FoodResponse> findMenusByRestaurantId(Long restaurantId) {
//        List<Food> foods = foodRepository.findAllByRestaurantId(restaurantId);
//        ArrayList<MenuResponse> menuResponses = new ArrayList<>();
//        for (Food food : foods) {
//           MenuResponse menuResponse = MenuResponse.of(food);
//           menuResponses.add(menuResponse);
//        }
//
//        return menuResponses;
        // 위와 동일한 코드 입니다. stream api를 공부하는 것도 좋은 학습이라 생각해서 작성해봤습니다.
        // of는 정적팩토리메서드 패턴 이라는 것을 활용했습니다 이펙티브 자바 책을 참고하면 좋습니다.
        return foodRepository.findAllByRestaurantId(restaurantId)
            .stream()
            .map(FoodResponse::of)
            .collect(Collectors.toList());
    }
}
