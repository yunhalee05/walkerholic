package com.yunhalee.walkerholic.product.dto;

import com.yunhalee.walkerholic.product.domain.ProductImage;
import lombok.Getter;

@Getter
public class ProductImageResponse {

    private String name;
    private String imageUrl;

    public ProductImageResponse(ProductImage productImage) {
        this.name = productImage.getName();
        this.imageUrl = productImage.getFilePath();
    }
}
