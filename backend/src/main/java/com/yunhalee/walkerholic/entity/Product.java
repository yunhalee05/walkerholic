package com.yunhalee.walkerholic.entity;

import com.yunhalee.walkerholic.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String name;

    private String description;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Integer price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> productImages = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();

//    @Transient
//    public List<String> getProductImageUrl(){
//        List<String> productImageUrl= new ArrayList<>();
//        this.productImages.forEach(productImage -> productImageUrl.add(productImage.getFilePath()));
//        return productImageUrl;
//    }

    //비지니스 로직
    public void addStock(Integer qty){
        this.stock += qty;
    }

    public void removeStock(Integer qty){
        Integer restStock = this.stock - qty;
        if(restStock <0){
            throw new NotEnoughStockException("Stock is not enough.");
        }
        this.stock = restStock;
    }

    @Transient
    public String getMainImageUrl(){
        return this.productImages.get(0).getFilePath();
    }

}
