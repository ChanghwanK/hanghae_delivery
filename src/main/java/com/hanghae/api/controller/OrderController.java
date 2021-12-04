package com.hanghae.api.controller;

import com.hanghae.api.dto.request.OrderRequestDto;
import com.hanghae.api.dto.response.OrderResponse;
import com.hanghae.api.service.OrderService;
import java.util.List;
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
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/request")
    public ResponseEntity<OrderResponse> registNewOrder (
        @RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity.ok().body(orderService.registNewOrder(orderRequestDto));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> findOrderById () {
        return ResponseEntity.ok().body(orderService.findAllOrder());
    }
}
