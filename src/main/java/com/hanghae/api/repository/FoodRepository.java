package com.hanghae.api.repository;

import com.hanghae.api.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */


public interface FoodRepository extends JpaRepository<Food, Long> {

}
