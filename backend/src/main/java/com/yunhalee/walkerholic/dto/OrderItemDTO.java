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

    private Integer productId;

    private Integer orderId;

    public OrderItemDTO(Integer id, Integer qty, Integer price, Integer productId, Integer orderId) {
        this.id = id;
        this.qty = qty;
        this.price = price;
        this.productId = productId;
        this.orderId = orderId;
    }

    public OrderItemDTO(Integer qty, Integer price, Integer productId, Integer orderId) {
        this.qty = qty;
        this.price = price;
        this.productId = productId;
        this.orderId = orderId;
    }
}
