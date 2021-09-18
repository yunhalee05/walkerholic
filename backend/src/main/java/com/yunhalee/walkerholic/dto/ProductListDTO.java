package com.yunhalee.walkerholic.dto;

import com.yunhalee.walkerholic.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductListDTO {

    private Integer id;

    private String name;

    private Integer stock;

    private Integer price;

    private Float average;

    private String imageUrl;

    public ProductListDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.stock = product.getStock();
        this.price = product.getPrice();
        this.average = product.getAverage();
        this.imageUrl = product.getMainImageUrl();
    }
}
