package com.hanghae.api.repository;

import com.hanghae.api.model.Food;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */


public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findAllByRestaurantId(Long restaurantId);
}
