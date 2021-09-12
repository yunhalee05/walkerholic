package com.yunhalee.walkerholic.dto;

import com.yunhalee.walkerholic.entity.Address;
import com.yunhalee.walkerholic.entity.OrderItem;
import com.yunhalee.walkerholic.entity.OrderStatus;
import com.yunhalee.walkerholic.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class OrderCreateDTO {

    private Integer id;

    private String paymentMethod;

    private AddressDTO address;

    private List<OrderItemDTO> orderItems;

    private Integer userId;

    public OrderCreateDTO(Integer id, String paymentMethod, AddressDTO address, List<OrderItemDTO> orderItems, Integer userId) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.address = address;
        this.orderItems = orderItems;
        this.userId = userId;
    }

    public OrderCreateDTO(String paymentMethod, AddressDTO address, List<OrderItemDTO> orderItems, Integer userId) {
        this.paymentMethod = paymentMethod;
        this.address = address;
        this.orderItems = orderItems;
        this.userId = userId;
    }
}
