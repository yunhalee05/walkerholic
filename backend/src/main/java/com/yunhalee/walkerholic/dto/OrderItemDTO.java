package com.yunhalee.walkerholic.dto;

import com.yunhalee.walkerholic.entity.Order;
import com.yunhalee.walkerholic.entity.Product;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderItemDTO {

    private Integer id;

    private Integer qty;

    private Integer price;

    private Product product;

    private Order order;
}
