package com.hanghae.api.repository;

import com.hanghae.api.model.Food;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */


public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findAllByRestaurantId(Long restaurantId);

    @Query("select f.name from Food f where f.restaurant.id = :restaurantId")
    List<String> findFoodNameByRestaurantId(Long restaurantId);
}
