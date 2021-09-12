package com.yunhalee.walkerholic.controller;

import com.yunhalee.walkerholic.dto.AddressDTO;
import com.yunhalee.walkerholic.dto.OrderCreateDTO;
import com.yunhalee.walkerholic.dto.OrderDTO;
import com.yunhalee.walkerholic.dto.OrderItemDTO;
import com.yunhalee.walkerholic.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderCreateDTO orderCreateDTO) {
        AddressDTO addressDTO = orderCreateDTO.getAddress();
        List<OrderItemDTO> orderItemDTOS = orderCreateDTO.getOrderItems();

        return new ResponseEntity<OrderDTO>(orderService.createOrder(orderCreateDTO), HttpStatus.CREATED);
    }

    @PostMapping("/order/cancel/{id}")
    public String deleteOrder(@PathVariable("id") String id) {
        Integer orderId = Integer.parseInt(id);
        return orderService.cancelOrder(orderId);
    }

    @GetMapping("/order/{id}")
    public OrderDTO getOrder(@PathVariable("id") String id) {
        Integer orderId = Integer.parseInt(id);
        return orderService.getOrder(orderId);
    }

}
