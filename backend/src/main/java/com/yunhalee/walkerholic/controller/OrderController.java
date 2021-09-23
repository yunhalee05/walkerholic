package com.yunhalee.walkerholic.controller;

import com.yunhalee.walkerholic.dto.*;
import com.yunhalee.walkerholic.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderCreateDTO orderCreateDTO) {
        AddressDTO addressDTO = orderCreateDTO.getAddress();
        List<OrderItemCreateDTO> orderItemCreateDTOS = orderCreateDTO.getOrderItems();

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

    @GetMapping("/cartItems/{id}")
    public OrderCartDTO getCart(@PathVariable("id")String id){
        Integer userId = Integer.parseInt(id);
        return orderService.getCart(userId);
    }

    @PostMapping("/createCart/{id}")
    public Integer createCart(@PathVariable("id")String id) {
        Integer userId = Integer.parseInt(id);
        return orderService.createCart(userId);
    }

    @PostMapping("/addToCart/{id}")
    public OrderItemDTO addToCart(@PathVariable("id")String id, @RequestBody OrderItemCreateDTO orderItem){
        Integer orderId = Integer.parseInt(id);
        System.out.println(orderItem);
        return orderService.addToCart(orderId, orderItem);
    }

    @GetMapping("/orderlist/{page}")
    public ResponseEntity<?> getOrderList(@PathVariable("page")String page){
        Integer pageNumber = Integer.parseInt(page);
        return new ResponseEntity<HashMap<String,Object>>(orderService.getOrderList(pageNumber),HttpStatus.OK);
    }

    @GetMapping("/orderlist/{page}/{id}")
    public ResponseEntity<?> getOrderListBySeller(@PathVariable("page")String page,@PathVariable("id")String id){
        Integer pageNumber = Integer.parseInt(page);
        Integer sellerId = Integer.parseInt(id);
        return new ResponseEntity<HashMap<String,Object>>(orderService.getOrderListBySeller(pageNumber,sellerId),HttpStatus.OK);
    }


}
