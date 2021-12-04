package com.hanghae.api.service;

import com.hanghae.api.dto.request.FoodRegistRequestDto;
import com.hanghae.api.dto.response.FoodResponse;
import com.hanghae.api.exception.FoodNameDuplicateException;
import com.hanghae.api.exception.FoodNotValidException;
import com.hanghae.api.exception.MinOrderPriceIsNot100UnitException;
import com.hanghae.api.exception.RestaurantNotFoundException;
import com.hanghae.api.model.Food;
import com.hanghae.api.model.Restaurant;
import com.hanghae.api.repository.FoodRepository;
import com.hanghae.api.repository.RestaurantRepository;
import java.util.ArrayList;
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

        List<String> savedFoodNameListByRestaurantId = foodRepository.findFoodNameByRestaurantId(
            restaurantId);
        List<String> requestFoodNames = new ArrayList<>();

        for(FoodRegistRequestDto foodRegistRequestDto : foodSaveRequestDtos) {
            requestFoodNames.add(foodRegistRequestDto.getName());
        }

        findDuplicateFoodNames(requestFoodNames);

        for(FoodRegistRequestDto foodRegistRequestDto : foodSaveRequestDtos) {
            int foodPrice = foodRegistRequestDto.getPrice();
            String requestDtoFoodName = foodRegistRequestDto.getName();

            checkFoodName(savedFoodNameListByRestaurantId, requestDtoFoodName);
            checkValidPrice(foodPrice); checkMinOrderIs100unit(foodPrice);

            Food food = foodRegistRequestDto.toEntity();
            food.registRestaurant(restaurant); foodRepository.save(food);
        }
    }

    @Transactional(readOnly = true)
    public List<FoodResponse> findMenusByRestaurantId (Long restaurantId) {
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
        return foodRepository.findAllByRestaurantId(restaurantId).stream().map(FoodResponse::of)
            .collect(Collectors.toList());
    }

    private void checkValidPrice (int price) {
        if(price < 100 || price > 1000000) {
            throw new FoodNotValidException();
        }
    }

    private void checkMinOrderIs100unit (int foodPrice) {
        int price = foodPrice;

        if((price % 100) != 0) {
            throw new MinOrderPriceIsNot100UnitException();
        }
    }

    private void checkFoodName (List<String> foodNameListByRestaurantId, String foodName) {
        for(String foodNameByRestaurantId : foodNameListByRestaurantId) {
            if(foodName.equals(foodNameByRestaurantId)) {
                throw new FoodNameDuplicateException();
            }
        }
    }

    private void findDuplicateFoodNames (List<String> requestFoodNames) {
        for(int i = 0; i < requestFoodNames.size(); i++) {
            String foodName_01 = requestFoodNames.get(i);
            for(int j = 0; j < requestFoodNames.size(); j++) {
                if(i == j) {
                    continue;
                }
                String foodNames_02 = requestFoodNames.get(j);
                if(foodName_01.equals(foodNames_02)) {
                    throw new FoodNameDuplicateException();
                }
            }
        }
    }
}


