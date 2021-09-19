package com.yunhalee.walkerholic.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCreateDTO {

    private Integer id;

    private String paymentMethod;

    private AddressDTO address;

    private List<OrderItemCreateDTO> orderItems;

    private Integer userId;

    public OrderCreateDTO(Integer id, String paymentMethod, AddressDTO address, List<OrderItemCreateDTO> orderItems, Integer userId) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.address = address;
        this.orderItems = orderItems;
        this.userId = userId;
    }

    public OrderCreateDTO(String paymentMethod, AddressDTO address, List<OrderItemCreateDTO> orderItems, Integer userId) {
        this.paymentMethod = paymentMethod;
        this.address = address;
        this.orderItems = orderItems;
        this.userId = userId;
    }
}
