package com.yunhalee.walkerholic.dto;

import com.yunhalee.walkerholic.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {

    private Integer id;
    private Integer qty;
    private Integer price;

    private Integer productId;
    private String productName;
    private String productDescription;
    private String productBrand;
    private String productImageUrl;

    public OrderItemDTO(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.qty = orderItem.getQty();
        this.price = orderItem.getPrice();
        this.productId = orderItem.getProduct().getId();
        this.productName = orderItem.getProduct().getName();
        this.productDescription = orderItem.getProduct().getDescription();
        this.productBrand = orderItem.getProduct().getBrand();
        this.productImageUrl = orderItem.getProduct().getMainImageUrl();
    }
}
