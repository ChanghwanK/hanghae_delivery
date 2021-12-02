package com.hanghae.api.repository;

import com.hanghae.api.model.Order;
import com.hanghae.api.model.OrderLine;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Created by Bloo
 * @Date: 2021/12/03
 */


public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

    List<OrderLine> findAllByOrder(Order order);
}
