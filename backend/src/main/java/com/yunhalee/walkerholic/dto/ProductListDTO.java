package com.yunhalee.walkerholic.dto;

import com.yunhalee.walkerholic.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductListDTO {

    private Integer id;

    private String name;

    private String brand;

    private String category;

    private Integer stock;

    private Float price;

    private Float average;

    private List<String> imagesUrl;

    public ProductListDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.brand = product.getBrand();
        this.category = product.getCategory().name();
        this.stock = product.getStock();
        this.price = product.getPrice();
        this.average = product.getAverage();
        this.imagesUrl = product.getImagesUrl();
    }
}
