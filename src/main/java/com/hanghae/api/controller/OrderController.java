package com.hanghae.api.controller;

import com.hanghae.api.dto.request.OrderRequestDTO;
import com.hanghae.api.dto.response.OrderFindResponseDTO;
import com.hanghae.api.dto.response.OrderResponseDTO;
import com.hanghae.api.service.OrderService;
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
public class OrderController {

    private final OrderService orderService;

    @PostMapping ("/api/user/order")
    public ResponseEntity<OrderResponseDTO> registNewOrder (
        @RequestBody OrderRequestDTO orderRequestDTO ) {
        return ResponseEntity.ok().body(orderService.registNewOrder(orderRequestDTO));
    }

    @GetMapping ("/api/user/order/{orderId}")
    public ResponseEntity<OrderFindResponseDTO> findOrderById ( @PathVariable (name = "orderId") Long id ) {
        return ResponseEntity.ok().body(orderService.findOrderById(id));
    }
}
