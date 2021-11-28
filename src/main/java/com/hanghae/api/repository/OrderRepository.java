package com.hanghae.api.repository;

import com.hanghae.api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Created by Bloo
 * @Date: 2021/11/28
 */


public interface OrderRepository extends JpaRepository<Order, Long> {

}
