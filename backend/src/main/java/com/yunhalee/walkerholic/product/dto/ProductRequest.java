package com.yunhalee.walkerholic.product.dto;

import com.yunhalee.walkerholic.post.domain.Post;
import com.yunhalee.walkerholic.product.domain.Category;
import com.yunhalee.walkerholic.product.domain.Product;
import com.yunhalee.walkerholic.user.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ProductRequest {

    private Integer id;

    private String name;

    private String description;

    private String brand;

    private String category;

    private Integer stock;

    private Float price;

    private Integer userId;

    public ProductRequest(String name, String description, String brand, String category, Integer stock, Float price, Integer userId) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.userId = userId;
    }

    public ProductRequest(Integer id, String name, String description, String brand, String category, Integer stock, Float price, Integer userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.userId = userId;
    }

    public Product toProduct(User user) {
        return Product.of(name, description, brand, Category.valueOf(category), stock, price, user);
    }

    public Product toProduct() {
        return new Product(name, brand, Category.valueOf(category), stock, price, description);
    }

}
