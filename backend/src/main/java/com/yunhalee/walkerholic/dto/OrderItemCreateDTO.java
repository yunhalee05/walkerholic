package com.yunhalee.walkerholic.dto;

import com.yunhalee.walkerholic.entity.Order;
import com.yunhalee.walkerholic.entity.OrderItem;
import com.yunhalee.walkerholic.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class OrderItemCreateDTO {

    private Integer id;

    private Integer qty;

    private Integer price;

    private Integer productId;

    private Integer orderId;

    public OrderItemCreateDTO(OrderItem orderItem){
        this.id = orderItem.getId();
        this.qty = orderItem.getQty();
        this.price = orderItem.getPrice();
        this.productId = orderItem.getProduct().getId();
        this.orderId = orderItem.getOrder().getId();
    }



    public OrderItemCreateDTO(Integer id, Integer qty, Integer price, Integer productId, Integer orderId) {
        this.id = id;
        this.qty = qty;
        this.price = price;
        this.productId = productId;
        this.orderId = orderId;
    }

    public OrderItemCreateDTO(Integer qty, Integer price, Integer productId, Integer orderId) {
        this.qty = qty;
        this.price = price;
        this.productId = productId;
        this.orderId = orderId;
    }
}
